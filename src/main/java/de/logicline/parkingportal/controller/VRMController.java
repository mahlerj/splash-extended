package de.logicline.parkingportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import de.logicline.parkingportal.model.VRMEntity;
import de.logicline.parkingportal.service.VRMService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


@Controller
public class VRMController {

    @Autowired
    private VRMService vrmService;
    
    @RequestMapping(value = "/vrm/edit/{contractInfoId}", method = RequestMethod.GET)
    public @ResponseBody List<VRMEntity> listVRM(@PathVariable("contractInfoId") String contractInfoId, HttpServletRequest request){
    	// TODO implement check for UserRole is admin
    	String token = request.getHeader("token");
        List<VRMEntity> vrmEntityList = vrmService.getVrmList(token, contractInfoId);
        
        return vrmEntityList;
    }
    
   

    @RequestMapping(value = "/vrm/edit/{contractInfoId}", method = RequestMethod.PUT)
    public @ResponseBody void updateVrm(@PathVariable("contractInfoId") String contractInfoId,@RequestBody VRMEntity[] vrmEntityArray, HttpServletRequest request) {
    	String token = request.getHeader("token");    	 
    	List<VRMEntity> vrmEntityList = Arrays.asList(vrmEntityArray);
    	vrmService.updateVrm(token, contractInfoId, vrmEntityList);
        return;
    }
 
    @RequestMapping(value = "/vrm/edit/create/{contractInfoId}", method = RequestMethod.POST)
    public @ResponseBody void createVrm(@PathVariable("contractInfoId") String contractInfoId,@RequestBody VRMEntity[] vrmEntityArray, HttpServletRequest request){
    	String token = request.getHeader("token");
    	List<VRMEntity> vrmEntityList = Arrays.asList(vrmEntityArray);
    	vrmService.createVrm(token, contractInfoId, vrmEntityList);
    	
        return;
    }
    
    
}
