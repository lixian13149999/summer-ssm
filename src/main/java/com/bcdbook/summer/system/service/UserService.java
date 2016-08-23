package com.bcdbook.summer.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcdbook.summer.common.enums.BaseEnum.BackMsg;
import com.bcdbook.summer.system.dao.UserDao;
import com.bcdbook.summer.system.pojo.User;

@Service("userService")
public class UserService implements InitializingBean{
	@Resource
	private UserDao userDao;

	public String addUser(User user) {
		userDao.insert(user);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

//	public User getUserById(int userId) {
//		return this.userDao.selectById(userId);
//	}
//	
//	@Transactional
//	public boolean addUsers(List<User> users) {
//		for (int i = 0; i < users.size(); i++) {
//			userDao.insert(users.get(i));
////			if(i>2){
////				throw new RuntimeException();
////			}else{
////				userDao.insert(users.get(i));
////			}
//		}
//		return false;
//	}
//	
//	public boolean addUser(User user) {
//		// TODO Auto-generated method stub
//		//根据要添加的用户名查询数据库，验证是否存在同名用户，如果存在则不可以添加用户
//		String userName=user.getUserName();
//		if(userDao.selectByName(userName)==null){
//			//执行添加操作			
//			userDao.insertSelective(user);
//			return true;
//		}else{			
//			return false;
//			}		
//	}
//	
////	public boolean updateUser(User user) {
////		// TODO Auto-generated method stub		
////		//根据传入的字段更新数据库，更新操作不验证用户
////		//根据传入的用户id查找到要更新的用户信息（如果查找到就执行更新操作，如果找不到就返回false）
////		if(userDao.selectById(user.getId())!=null){			
////			//执行更新操作
////			userDao.updateByPrimaryKeySelective(user);			
////			return true;			
////		}else{	
////			
////			return false;
////		}		
////	}
//
////	public boolean deleteUser(User user) {
////		// TODO Auto-generated method stub
////		//获取要删除记录的id即用户的id；
////		int  userId=user.getId();
////		//如果对应的id存在就删除记录，返回true，如果对应的id不存在就返回false，删除失败
////		//根据id获取用户
////		if(userDao.selectById(userId)!=null){			
////			userDao.deleteById(userId);
////			return true;			
////		}else{
////			return false;			
////		}
////	}
//	/**
//	 * 
//	    * @Discription TODO
//	    * @author lason       
//	    * @created 2016年6月5日 下午4:36:47      
//	    * @param username
//	    * @param pwd
//	    * @param openId
//	    * @return     
//	    * @see com.bcdbook.summer.system.user.service.UserService#bindingWechat(java.lang.String, java.lang.String, java.lang.String)
//	 */
//	public String bindingWechat(String username, String pwd, String openId) {
//		// TODO Auto-generated method stub
//
//		//1.根据用户名和密码是否能找到用户（即验证对应用户名和密码的记录在数据库中是否存在）：存在，继续判断openId是否存在，不存在，绑定不成功
//		User user=userDao.selectByNameAndPassword(username, pwd);	
//		
//		if(user!=null){
//			//2.判断openId是否存在:存在，dataexist，不存在就绑定对应的openId到该记录并返回绑定成功提醒	
//			
//			if(user.getOpenId()==null||user.getOpenId().trim().equals("")){
//				System.out.println(user);
//				//添加新的openId
//			  user.setOpenId(openId);
//			  
//			  //执行保存（保存）操作
//			  userDao.updateByPrimaryKeySelective(user);
//			  return BackMsg.SUCCESS.getValue();//添加成功success
//			}else{				
//				return  BackMsg.DATA_EXIST.getValue();//data exist数据已经绑定需要解绑后重新绑定
//			}			
//		}else{			 
//			return BackMsg.DATA_IS_NULL.getValue();//data is null
//		}			
//	
//
//	}
//
//	public String bindingEmail(String username, String pwd, String email) {
//		// TODO Auto-generated method stub
//
//		//1.根据用户名和密码是否能找到用户（即验证对应用户名和密码的记录在数据库中是否存在）：存在，继续判断email是否存在，不存在绑定-->成功
//				User user=userDao.selectByNameAndPassword(username, pwd);
//				
//				if(user!=null){
//					//2.判断openId是否存在:存在，dataexist，不存在就绑定对应的openId到该记录并返回绑定成功提醒			
//					if(user.getEmail()==null||user.getEmail().trim().equals("")){
//						//添加新的email
//					  user.setOpenId(email);
//					  //执行保存操作
//					  userDao.updateByPrimaryKeySelective(user);	
//					  return BackMsg.SUCCESS.getValue();
//					}else{				
//						 return  BackMsg.DATA_EXIST.getValue();//data exist数据已经绑定需要解绑后重新绑定
//					}			
//				}else{			
//					return BackMsg.DATA_IS_NULL.getValue();//data is null
//				}			
//				
//
//	}

}
