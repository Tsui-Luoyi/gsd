package com.jsd.web.vehicle.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsd.web.vehicle.dao.TerminalRenewDao;
import com.jsd.web.vehicle.vo.TerminalRenewVO;

public class TerminalRenewDaoImpl extends HibernateDaoSupport implements
		TerminalRenewDao {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TerminalRenewVO> findTerminalRenew(TerminalRenewVO vo, String vids) {
		//必须是绑定好的终端与车辆
		String sql = "select tv.vname as vname, tvd.dno as dno, tvd.simnumber as simnum," +
				"tvd.renewdate as lastrenewdate, tvd.renewperiod as renewperiod " +
				"from tvehicle_info tv inner join " +
				"(select tvv.vid,tdr.dno as dno,tdr.simnumber as simnumber," +
				"tdr.renewdate as renewdate, tdr.renewperiod as renewperiod " +
				"from tvv_band tvv inner join " +
				"(select dr.renewdate as renewdate, dr.renewperiod as renewperiod, " +
				"di.dno as dno, di.simnumber as simnumber, di.did as did " +
				"from devterm_info di left join devterm_renew dr on di.dno = dr.dno) tdr " +
				"on tvv.did = tdr.did) tvd on tvd.vid = tv.vid " +
				"where tv.vid in (" + vids + ")";

		sql = getSqlAddCondition(sql, vo);

		final String sqlstr = sql;

		List list = (List) this.getHibernateTemplate().execute(new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sqlstr);
				session.close();
				return query.list();
			}
		});

		return constructList(list);
	}

	private List<TerminalRenewVO> constructList(List<Object[]> list) {
		List<TerminalRenewVO> terminalRenewVOList = new ArrayList<TerminalRenewVO>();

		if(list != null && list.size() > 0){
			for(Object[] obj : list){
				TerminalRenewVO vo = new TerminalRenewVO();
				vo.setVname(getObj(obj[0]));
				vo.setDno(getObj(obj[1]));
				vo.setSimnum(getObj(obj[2]));
				vo.setLastrenewdate(getObj(obj[3]));
				vo.setRenewperiod(getObj(obj[4]));

				terminalRenewVOList.add(vo);
			}
		}

		return terminalRenewVOList;
	}

	public String getObj(Object obj){
		if(obj == null){
			return "";
		}else{
			if(obj instanceof Integer){
				return ((Integer)obj).toString();
			}else if(obj instanceof String){
				return (String)obj;
			}else if(obj instanceof Double){
				return ((Double)obj).toString();
			}else if(obj instanceof Float){
				return ((Float)obj).toString();
			}else if(obj instanceof Long){
				return ((Long)obj).toString();
			}else if(obj instanceof Boolean){
				return ((Boolean)obj).toString();
			}else if(obj instanceof Date){
				return ((Date)obj).toString();
			}else if(obj instanceof Timestamp){
				return ((Timestamp)obj).toString();
			}else{
				return "";
			}
		}
	}

	private String getSqlAddCondition(String sql, TerminalRenewVO vo) {
		if(vo.getVname() != null && !"".equals(vo.getVname().trim())){
			sql += " and tv.vname like '%"+ vo.getVname().trim()+"%'";
		}

		if(vo.getDno() != null && !"".equals(vo.getDno().trim())){
			sql += " and tvd.dno like '%"+ vo.getDno().trim()+"%'";
		}

		if(vo.getSimnum() != null && !"".equals(vo.getSimnum().trim())){
			sql += " and tvd.simnumber like '%"+ vo.getSimnum().trim()+"%'";
		}

		if(vo.getLastrenewdate() != null && !"".equals(vo.getLastrenewdate().trim())){
			sql += " and tvd.renewdate = '" + vo.getLastrenewdate().trim() + "'";
		}

		if(vo.getRenewperiod() != null && !"".equals(vo.getRenewperiod().trim())){
			sql += " and tvd.renewperiod = '" + vo.getRenewperiod() + "'";
		}

		sql += " order by tvd.renewdate desc";

		return sql;
	}

	@Override
	@SuppressWarnings({ "deprecation", "resource" })
	public void modifyTerminalRenew(String[] sqls) {
		Session session = this.getSession();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			session.beginTransaction();//开启事务
			conn = session.connection();//打开连接

			ps = conn.prepareStatement(sqls[0]);
			ps.executeUpdate();

			ps = conn.prepareStatement(sqls[1]);
			ps.executeQuery();
			rs = ps.getResultSet();
			if (rs.next()) {//该终端在终端续费管理表中存在,更新
				ps = conn.prepareStatement(sqls[3]);
			}else{//不存在则插入记录
				ps = conn.prepareStatement(sqls[2]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();//回滚事务
		}finally{
			try {
				rs.close();//关闭记录集
				ps.close();//关闭预处理
				conn.close();//关闭连接
				session.getTransaction().commit();//提交事务
				session.close();//关闭回话
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
