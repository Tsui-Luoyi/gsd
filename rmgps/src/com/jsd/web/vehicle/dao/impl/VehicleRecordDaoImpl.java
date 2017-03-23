package com.jsd.web.vehicle.dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.po.DevtermInfo;
import com.jsd.web.po.TvehicleInfoRecord007;
import com.jsd.web.vehicle.dao.VehicleRecordDao;

@SuppressWarnings({"rawtypes","unchecked"})
public class VehicleRecordDaoImpl extends HibernateDaoSupport implements
		VehicleRecordDao {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String getObj(Object obj) {
		if (obj == null) {
			return "";
		} else {
			if (obj instanceof Integer) {
				return ((Integer) obj).toString();
			} else if (obj instanceof String) {
				return (String) obj;
			} else if (obj instanceof Double) {
				return ((Double) obj).toString();
			} else if (obj instanceof Float) {
				return ((Float) obj).toString();
			} else if (obj instanceof Long) {
				return ((Long) obj).toString();
			} else if (obj instanceof Boolean) {
				return ((Boolean) obj).toString();
			} else if (obj instanceof Date) {
				return ((Date) obj).toString();
			} else if (obj instanceof Timestamp) {
				return ((Timestamp) obj).toString();
			} else {
				return "";
			}
		}
	}

	@Override
	public TvehicleInfoRecord007 findVehicleRecord007(String vno) {
		final String sql = "SELECT * FROM `tvehicle_info` Where `vno` = '"
				+ vno + "'";
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								TvehicleInfoRecord007.class);
						session.flush();
						session.close();
						return query.list();
					}
				});

		return (TvehicleInfoRecord007) list.get(0);

	}

	@Override
	public boolean modifyEngineNo007(String vno, String vname, String engineNo) {
		SessionFactory factory = this.getHibernateTemplate().getSessionFactory();
		org.hibernate.classic.Session session = factory.openSession();
		boolean flag=false;
		try {
			String sql = "update tvehicle_info tv set tv.engine_no = '"
					+ engineNo.trim() + "', tv.vname= '"+vname.trim()+"' where vno = '" + vno+"'";
			int result = session.createSQLQuery(sql).executeUpdate();
			if(result >0)
				flag=true;
			return flag;
		} catch (Exception e) {
			System.out.println("整机档案保存错误");
			return flag;
		}finally{
			session.close();
		}


	}

	@Override
	public DevtermInfo findDevtermRecord007(String vno) {
		final String hql = "select new DevtermInfo(d.dno,d.manufacturer,d.simnumber) from DevtermInfo d where d.dno = '" + vno +"'";

		DevtermInfo dInfo = (DevtermInfo) this.getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						session.flush();
						session.close();
						return query.uniqueResult();
					}
				});

		return dInfo;
	}

	@Override
	public void findOtherRecord007(String vno) {
		// TODO Auto-generated method stub

	}

	@Override
	public TvehicleInfoRecord007 findvm_id(String vno) {
		// TODO Auto-generated method stub

		final String hql = "select new TvehicleInfoRecord007(t.vid,t.vm_id) from TvehicleInfoRecord007 t where t.vno = '" + vno +"'";
		TvehicleInfoRecord007 tInfo = (TvehicleInfoRecord007) this.getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						session.flush();
						session.close();
						return query.uniqueResult();
					}
				});

		return tInfo;
	}

	@Override
	public String findtvModel(Integer vm_id) {
		// TODO Auto-generated method stub

		final String sql = "select mname from tvehicle_model where vm_id = " + vm_id;
		String mname = (String) this.getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						session.flush();
						session.close();
						return query.uniqueResult();
					}
				});

		return mname;
	}

}
