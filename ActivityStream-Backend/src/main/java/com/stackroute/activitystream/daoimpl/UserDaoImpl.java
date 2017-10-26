package com.stackroute.activitystream.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.activitystream.dao.UserDAO;
import com.stackroute.activitystream.model.User;

@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDAO {

	Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		this.sessionFactory = sessionFactory;
	}

	public Boolean register(User user) {
		// TODO Auto-generated method stub
		try {
			user.setStatus("A");
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public User validate(User user) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from User where email = ? and password = ?");
			query.setParameter(0, user.getEmail());
			query.setParameter(1, user.getPassword());
			user = (User) query.uniqueResult();
			log.debug("User :: " + user.getFirstName() + user.getLastName() + " is authenticated");
			return user;
		} catch (Exception e) {
			log.debug("Login Failed!!");
			return null;
		}
	}

	public Boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean delete(String emailid) {

		try {
			User updateUser = getUser(emailid);
			updateUser.setStatus("N");
			sessionFactory.getCurrentSession().update(updateUser.getEmail(), updateUser);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private User getUser(String emailid) {

		try {
			User user = (User) sessionFactory.getCurrentSession().get(User.class, emailid);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public List<User> getAllUsers() {
		try {
			List<User> user = sessionFactory.getCurrentSession().createQuery("from User").list();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

}
