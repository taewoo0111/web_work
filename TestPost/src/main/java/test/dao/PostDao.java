package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.PostDto;
import test.util.DbcpBean;

public class PostDao {
	
	private static PostDao dao;
	
	static {
		dao = new PostDao();
	}
	
	private PostDao() {}
	
	public static PostDao getInstance() {
		return dao;
	}
	
	// test_posts 테이블의 전체 글 개수를 int 타입으로 반환하는 메소드
	public int getCount() {
		
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = """
					select count(*) as count 
					from test_posts
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩할게 있으면 여기서 하기
			//sql 문 실행하고 결과를 ResultSet 객체로 리턴받기
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return count;
	}
	
	public boolean insert(PostDto dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					insert into test_posts
					(num, title, writer, content)
					values(test_posts_seq.nextval, ?, ?, ?)
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
	}
	
	public boolean update(PostDto dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					update test_posts
					set title = ?, content = ?, updatedat = sysdate
					where num = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
	}
	
	// test_readed 테이블에서 해당 세션으로 로그인된 유저가 글을 읽었는지를 기록
	public boolean isReaded(int num, String id) {
		
		boolean isReaded = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = """
				SELECT *
				FROM test_readed
				WHERE num=? AND session_id=?
			""";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, num);
			pstmt.setString(2, id);
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//만일 select 된 row 가 존재 한다면 이미 읽은것이다 
			if (rs.next()) {
				isReaded=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); 
			} catch (Exception e) {
			}
		}
		return isReaded;
	}
	
	// test_posts 테이블의 viewcount를 처음 읽었을 때 값이 증가하게 하는 메소드
	public boolean addViewCount(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = """
				UPDATE test_posts
				SET viewCount = viewCount+1
				WHERE num=?
			""";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, num);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return rowCount>0;
	}

	// test_posts 테이블을 삭제하려면 num을 참조하는 test_readed 테이블을 먼저 삭제해야함
	public boolean delete(int num) {
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
            conn = new DbcpBean().getConn();
            String sql = """
                DELETE FROM test_posts
                WHERE num=?
            """;
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, num);
            rowCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowCount > 0;
	}

	public PostDto getData(int num) {
		
		PostDto dto = null;
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = new DbcpBean().getConn();
            String sql = """
                SELECT writer, title, content, viewcount, createdAt, updatedAt
                FROM test_posts
                WHERE num=?
            """;
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, num);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto = new PostDto();
                dto.setNum(num);
                dto.setWriter(rs.getString("writer"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setViewcount(rs.getInt("viewcount"));
                dto.setCreatedAt(rs.getString("createdAt"));
                dto.setUpdatedAt(rs.getString("updatedAt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dto;
	}

	public List<PostDto> getListAll(){
		
		List<PostDto> list = new ArrayList<>();
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = new DbcpBean().getConn();
            String sql = """
                SELECT num, writer, title, content, viewcount, createdAt, updatedAt
                FROM test_posts
                ORDER BY num DESC
            """;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PostDto dto = new PostDto();
                dto.setNum(rs.getInt("num"));
                dto.setWriter(rs.getString("writer"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setViewcount(rs.getInt("viewcount"));
                dto.setCreatedAt(rs.getString("createdAt"));
                dto.setUpdatedAt(rs.getString("updatedAt"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		return list;
	}
	
	// test_readed 테이블에 특정 유저가 읽었는지를 기록
	public boolean insertReaded(int num, String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = """
				INSERT INTO test_readed
				(num, session_id)
				VALUES(?, ?)
			""";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, num);
			pstmt.setString(2, id);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return rowCount>0;
	}

	// test_posts의 글을 삭제하려면 num을 참조하는 test_readed의 테이블을 먼저 삭제해줘야함
	public boolean deleteReaded(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = """
				DELETE FROM test_readed
				WHERE num=?
			""";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, num);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return rowCount>0;
	}
	
	// 페이징 처리를 할 메소드 -> 몇개의 row로 나눠서 페이징 처리할건지 between ? and ? 으로 구해줘야함
	public List<PostDto> getList(PostDto dto) {
		
		List<PostDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			StringBuilder sql = new StringBuilder();
			sql.append("""
					select * from
					(select result1.*, rownum as rnum from
					(select num, writer, title, viewcount, to_char(createdat, 'YYYY.MM.DD HH24:MI') as createdat from test_posts
					order by num desc) result1) where rnum between ? and ?
					""");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getStartRowNum());
			pstmt.setInt(2, dto.getEndRowNum());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostDto tmp = new PostDto();
				tmp.setNum(rs.getInt("num"));
				tmp.setWriter(rs.getString("writer"));
				tmp.setTitle(rs.getString("title"));
				tmp.setViewcount(rs.getInt("viewcount"));
				tmp.setCreatedAt(rs.getString("createdat"));
				list.add(tmp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	return list;
	}
}
