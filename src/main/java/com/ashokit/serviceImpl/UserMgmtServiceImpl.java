package com.ashokit.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.bindings.LoginForm;
import com.ashokit.bindings.UnlockAccForm;
import com.ashokit.bindings.UserRegForm;
import com.ashokit.entity.CityMasterEntity;
import com.ashokit.entity.CountryMasterEntity;
import com.ashokit.entity.StateMasterEntity;
import com.ashokit.entity.UserDetailsEntity;
import com.ashokit.repository.CityMasterRepository;
import com.ashokit.repository.CountryMasterRepository;
import com.ashokit.repository.StateMasterRepository;
import com.ashokit.repository.UserDetailsRepository;
import com.ashokit.service.UserMgmtService;
import com.ashokit.utils.EmailUtils;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private CountryMasterRepository countryMasterRepository;
	
	@Autowired
	private StateMasterRepository stateMasterRepository;
	
	@Autowired
	private CityMasterRepository cityMasterRepository;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String loginCheck(LoginForm loginForm) {
		UserDetailsEntity userDetails=userDetailsRepository.findByEmailAndPazzword(loginForm.getEmail(), loginForm.getPassword());
		
		if(userDetails==null) {
			return "Invalid credntials";
		}
		if(userDetails.getAccStatus().equals("LOCKED")) {
			return "Your Account Locked";
		}
		return "SUCCESS";
	}

	@Override
	public String emailCheck(String emailId) {
		UserDetailsEntity userDetails=userDetailsRepository.findByEmail(emailId);
		if(userDetails==null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMasterEntity> findAll=countryMasterRepository.findAll();
		Map<Integer, String> countryMap=new HashMap<Integer, String>();
		findAll.forEach(country -> {
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {
		List<StateMasterEntity> states=stateMasterRepository.findByCountryId(countryId);
		Map<Integer,String> stateMap=new HashMap<Integer,String>();
		states.forEach(state -> {
			stateMap.put(state.getStateId(), state.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		List<CityMasterEntity> cities=cityMasterRepository.findByStateId(stateId);
		Map<Integer,String> citiesMap=new HashMap<Integer,String>();
		cities.forEach(city -> {
			citiesMap.put(city.getCityId(), city.getCityName());
		});
		return citiesMap;
	}

	@Override
	public String saveUser(UserRegForm userRegForm) {
		userRegForm.setPazzword(generateRandomPwd(6));
		
		UserDetailsEntity entity= new UserDetailsEntity();
		entity.setAccStatus("LOCKED");
		BeanUtils.copyProperties(userRegForm, entity);
		userDetailsRepository.save(entity);
		
		String subject = "User Registration - AshokIt ";
		String emailBody= readUnlockAccEmailBody(userRegForm);
		emailUtils.sendMail(userRegForm.getEmail(), subject, emailBody);
		return "SUCCESS";
	}

	@Override
	public String unlockAcc(UnlockAccForm unlockAccForm) {
		UserDetailsEntity userDetails=userDetailsRepository.findByEmailAndPazzword(unlockAccForm.getEmail(), unlockAccForm.getTempPwd());
		if(userDetails==null) {
			return "Invalid temporary Password";
		}
		userDetails.setPazzword(unlockAccForm.getNewPwd());
		userDetails.setAccStatus("UNLOCKED");
		userDetailsRepository.save(userDetails);
		return "ACCOUNT UNLOCKED SUCCESSFULLY";
	}

	@Override
	public String forgotPwd(String emailId) {
		UserDetailsEntity userDetails=userDetailsRepository.findByEmail(emailId);
		if(userDetails==null) {
			return "Invalid Email";
		}
		
		String subject  = "Recover Password - AshokIt";
		String body = readRecoverPwdEmailBody(userDetails);
		emailUtils.sendMail(emailId, subject, body);
		
		return "Password sent to your registered email";
	}
	
	private String generateRandomPwd(int n)
    {
     // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
        return sb.toString();
    }
	
	private String readUnlockAccEmailBody(UserRegForm user) {
		String body="";
		StringBuffer buffer=new StringBuffer();
		Path filePath= Paths.get("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
		
		try(Stream<String> stream = Files.lines(filePath)) {
			stream.forEach(line -> {
				buffer.append(line);
			});
			
			body = buffer.toString();
			body = body.replace("{FNAME}", user.getFName());
			body = body.replace("{LNAME}", user.getLName());
			body = body.replace("{TEMP-PWD}", user.getPazzword());
			body = body.replace("{EMAIL}", user.getEmail());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return body;
	}
	
	private String readRecoverPwdEmailBody(UserDetailsEntity entity) {
		String body="";
		StringBuffer buffer=new StringBuffer();
		Path filePath= Paths.get("RECOVCER-PASSWORD-EMAIL-BODY-TEMPLATE.txt");
		
		try(Stream<String> stream = Files.lines(filePath)) {
			stream.forEach(line -> {
				buffer.append(line);
			});
			
			body = buffer.toString();
			body = body.replace("{FNAME}", entity.getFName());
			body = body.replace("{LNAME}", entity.getLName());
			body = body.replace("{PWD}", entity.getPazzword());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	

}
