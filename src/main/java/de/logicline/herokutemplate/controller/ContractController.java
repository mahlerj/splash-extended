package de.logicline.herokutemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.logicline.herokutemplate.model.ContractInfoEntity;
import de.logicline.herokutemplate.service.ContractService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContractController {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/contract/list", method = RequestMethod.GET)
    public @ResponseBody List<ContractInfoEntity> getContractList(HttpServletRequest request, HttpServletResponse response) {      
    	String token = request.getHeader("token");
    	List<ContractInfoEntity> contractList = contractService.getContractList(token);

    	return contractList; 	
    }
    
    @RequestMapping(value = "/contract/list/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<ContractInfoEntity> getContractListByUserId(@PathVariable("userId") Integer userId, HttpServletRequest request, HttpServletResponse response) {      
    	
    	// TODO implement check for UserRole is admin
    	String token = request.getHeader("token");
    	
    	List<ContractInfoEntity> contractList = contractService.getContractList(userId);

    	return contractList; 	
    }
    
    
   
    @RequestMapping(value = "/contract/edit/{contractInfoId}", method = RequestMethod.GET)
    public @ResponseBody  Map<String,Object> getContractInfo(@PathVariable("contractInfoId") String contractInfoId, HttpServletRequest request, HttpServletResponse response) {      
    	// TODO implement check for UserRole is admin
    	String token = request.getHeader("token");
    	
    	Map<String,Object> contractInfoMap = contractService.getContractInfo(token, contractInfoId);
    	
    	return  contractInfoMap; 	
    }
    
    @RequestMapping(value = "/contract/edit/{contractInfoId}", method = RequestMethod.PUT)
    public @ResponseBody void updateContractInfo(@PathVariable("contractInfoId") String contractInfoId, @RequestBody ContractInfoEntity contractInfo, HttpServletRequest request) {      
    	// TODO implement check for UserRole is admin
    	String token = request.getHeader("token");
    	contractService.updateContractInfo(contractInfoId, contractInfo);
    	
    	return; 	
    }
    
   
 
}
