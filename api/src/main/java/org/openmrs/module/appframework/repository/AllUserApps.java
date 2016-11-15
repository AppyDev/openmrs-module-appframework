/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.appframework.repository;

import java.util.List;

import org.openmrs.api.db.hibernate.DbSessionFactory;  
import org.openmrs.module.appframework.domain.UserApp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AllUserApps {
	
	private DbSessionFactory sessionFactory;
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = true)
	public UserApp getUserApp(String appId) {
		try{
			UserApp user = (UserApp) sessionFactory.getCurrentSession().get(UserApp.class, appId);
			log.debug("Successfully retrieved user app.");
			return user;
		}catch(Exception e){
			log.debug("Error in getting user app.");
		}
			
		
	}
	
	@Transactional
	public UserApp saveUserApp(UserApp userApp) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(userApp);
			log.debug("User saved successfully.");
			return userApp;
		}catch(Exception e){
			log.debug("Error in saving user.");
		}		
	}
	
	@Transactional(readOnly = true)
	public List<UserApp> getUserApps() {
		try{
			List<UserApp> users = sessionFactory.getCurrentSession().createCriteria(UserApp.class).list();
			log.debug("Successfully retrived user apps.");
			return users;
		}catch(Exception e){
			log.debug("Error in getting user apps.");
		}
		
	}
	
	@Transactional
	public void deleteUserApp(UserApp userApp) {
		try{
			sessionFactory.getCurrentSession().delete(userApp);
			log.debug("User deleted successfully.");
			return userApp;
		}catch(Exception e){
			log.debug("Error in deleting user.");
		}
		
	}
	
}
