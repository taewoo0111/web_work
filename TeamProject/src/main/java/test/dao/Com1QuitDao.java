package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.Com1QuitDto;
import test.util.DbcpBean;

public class Com1QuitDao {

   private static Com1QuitDao dao;
   static {
      dao = new Com1QuitDao();
   }
   private Com1QuitDao() {}
   public static Com1QuitDao getInstance() {
      return dao;
   }
   
   
   
   
   // 리스트 개수만 가져오는 메소드
   public int getCount(Com1QuitDto dto) {
      
      
      
      
      System.out.println("getCount() 메소드 호출됨");
      System.out.println("getCondition(): " + dto.getCondition());
      System.out.println("getKeyword(): " + dto.getKeyword());
      
      
      
      int count = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         conn = new DbcpBean().getConn();
         
         // 쿼리문 생성
         StringBuilder sql = new StringBuilder();
         sql.append("SELECT COUNT(*) AS count FROM test_com1_quit");
         
         if(dto.getCondition() != null && dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
            sql.append("WHERE ").append(dto.getCondition()).append(" LIKE ? ");
         }
         
         pstmt = conn.prepareStatement(sql.toString()); 
         
         System.out.println(111);
         System.out.println(sql);
         System.out.println("%" + dto.getKeyword() + "%");
         
         // 검색조건이 있는 경우 LIKE ? 에 값 바인딩
         if(dto.getCondition() != null && dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
            pstmt.setString(1, "%" + dto.getKeyword() + "%");
         }
         
         System.out.println(222);
         System.out.println(sql);
         
         // 쿼리 실행 및 결과 추출
         rs = pstmt.executeQuery();
         System.out.println(333);
         if(rs.next()) {
            count = rs.getInt("count");
         }
         
      } catch(Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (Exception e) {e.printStackTrace();}
      }
      
      System.out.println("getCount() 메소드 리턴됨");
      return count;
   }
   
   
   
   
   
   
   // 리스트 정보를 가져오는 메소드
   public List<Com1QuitDto> getList(Com1QuitDto dto) {
      System.out.println("getList() 메소드 호출됨");
      List<Com1QuitDto> list = new ArrayList<>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         conn = new DbcpBean().getConn();
         
         // SQL 문 생성
         StringBuilder sql = new StringBuilder();
         sql.append("SELECT * FROM ");
         sql.append("(SELECT result1.*, ROWNUM AS rnum FROM ");
         sql.append("(SELECT empno, ename, storenum, role, ecall, TO_CHAR(hiredate, 'YYYY.MM.DD HH24:MI') AS hiredate, TO_CHAR(quitdate, 'YYYY.MM.DD HH24:MI') AS quitdate FROM test_com1_quit ");
         if(dto.getCondition() != null && dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
            sql.append("WHERE ").append(dto.getCondition()).append(" LIKE ? ");
         }
         sql.append("ORDER BY quitdate DESC) result1) WHERE rnum BETWEEN ? AND ?");
         // ? 바인딩
         pstmt = conn.prepareStatement(sql.toString());
         int paramIndex = 1;
         if(dto.getCondition() != null && dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
            pstmt.setString(paramIndex++, "%" + dto.getKeyword() + "%");
         }
         pstmt.setInt(paramIndex++, dto.getStartRowNum());
         pstmt.setInt(paramIndex, dto.getEndRowNum());
         // 쿼리 실행 및 결과 추출
         rs = pstmt.executeQuery();
         while(rs.next()) {
            Com1QuitDto tmp = new Com1QuitDto();
            tmp.setEmpNo(rs.getInt("empno"));
            tmp.seteName(rs.getString("ename"));
            tmp.setStoreNum(rs.getInt("storenum"));
            tmp.setRole(rs.getString("role"));
            tmp.seteCall(rs.getString("ecall"));
            tmp.setHiredate(rs.getString("hiredate"));
            tmp.setQuitdate(rs.getString("quitdate"));
            list.add(tmp);
         }
      } catch(Exception e) {
         System.out.println("오류 발생!!");
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (Exception e) {e.printStackTrace();}
      }
      System.out.println(list);
      System.out.println("getList() 메소드 리턴됨");
      return list;
   }
}
