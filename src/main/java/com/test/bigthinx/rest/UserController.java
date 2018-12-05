package com.test.bigthinx.rest;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.bigthinx.bo.BaseResponse;
import com.test.bigthinx.pojo.User;
import com.test.bigthinx.repo.UserRepo;
import com.test.bigthinx.service.UserService;


@RestController
public class UserController {

	@Autowired
	public UserService userService;

	@Autowired
	public UserRepo userRepo;
	/**
	 *JSON object:{
  "name":"Abhi",
  "dob" : "15/11/1994",
  "address":"Hyd",
  "description":"Software Engineer",
  "image":"",
  "username":"AbhiJ",
  "email":"Abhijit.Jadhav@gmail.com",
  "password":"Akhil@123"
  }
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/addUser",method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> addUser(@RequestBody User user){
		BaseResponse baseResponse = new BaseResponse();
		HttpStatus httpStatus = null;
		try{
			userService.addUser(user);
			baseResponse.setMessage("User added sucessfully");
			baseResponse.setStatus("success");
			httpStatus = HttpStatus.OK;
		}catch(Exception e) {
			baseResponse.setMessage("Not able to add user");
			baseResponse.setStatus("error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<BaseResponse>(baseResponse, httpStatus);
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> userLogin(@RequestBody User user){
		BaseResponse baseResponse = new BaseResponse();
		HttpStatus httpStatus = null;
		try{
			String token = userService.login(user);

			if(token==null)
				baseResponse.setMessage("Wrong Credentials");
			else
				baseResponse.setMessage("Token:" + token);
			baseResponse.setStatus("success");
			httpStatus = HttpStatus.OK;
		}catch(Exception e) {
			baseResponse.setMessage("Not able to add user");
			baseResponse.setStatus("error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<BaseResponse>(baseResponse, httpStatus);	
	}

	@RequestMapping(value="/getUsers",method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> getUsers(@RequestHeader("token") String token){
		BaseResponse baseResponse = new BaseResponse();
		HttpStatus httpStatus = null;
		try{			
			User user = userRepo.findByToken(token);
			if(user==null)
				baseResponse.setMessage("UnAuthorized User");	
			baseResponse.setMessage("Login sucessful");
			baseResponse.setStatus("success");
			httpStatus = HttpStatus.OK;
		}catch(Exception e) {
			baseResponse.setMessage("Not able to add user");
			baseResponse.setStatus("error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<BaseResponse>(baseResponse, httpStatus);
	}

	@RequestMapping(value="/deleteUser",method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> deleteUser(@RequestParam(value="id",required=false,defaultValue="0") String id){
		BaseResponse baseResponse = new BaseResponse();
		HttpStatus httpStatus = null;
		try{	
			userService.deleteById(new ObjectId(id));	
			baseResponse.setMessage("User deleted successfully");
			baseResponse.setStatus("success");
			httpStatus = HttpStatus.OK;
		}catch(Exception e) {
			baseResponse.setMessage("Not able to delete user");
			baseResponse.setStatus("error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<BaseResponse>(baseResponse, httpStatus);
	}

	@RequestMapping(value="/getAllUsers",method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){
		BaseResponse baseResponse = new BaseResponse();
		HttpStatus httpStatus = null;
		List<User> userList = new ArrayList<>();
		try{
			userList = userService.getAllusers();	
			baseResponse.setStatus("success");
			httpStatus = HttpStatus.OK;
		}catch(Exception e) {
			baseResponse.setMessage("Not able to get users");
			baseResponse.setStatus("error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(userList, httpStatus);
	}
	
	@RequestMapping(value="/updateUser",method = RequestMethod.PUT)
	public ResponseEntity<User> updateUserById(@RequestBody User user){
		BaseResponse baseResponse = new BaseResponse();
		HttpStatus httpStatus = null;
		User updatedUser = null; 
		List<User> userList = new ArrayList<>();
		try{
			updatedUser = userService.updateUserById(user);	
			baseResponse.setStatus("success");
			httpStatus = HttpStatus.OK;
		}catch(Exception e) {
			baseResponse.setMessage("Not able to update user");
			baseResponse.setStatus("error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(updatedUser, httpStatus);
	}

}
