package todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import todo.dto.TodoDto;

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/todolist?useSSL=false";
	private static String dbUser = "todouser";
	private static String dbpasswd = "1234";

	public TodoDto getTodo(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TodoDto dto = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "SELECT name,date_format(regdate, '%Y-%m-%d') regdate,sequence,title,type FROM todo WHERE id="
					+ id;
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String regDate = rs.getString("regdate");
				int sequence = rs.getInt("sequence");
				String title = rs.getString("title");
				String type = rs.getString("type");
				dto = new TodoDto(id, name, regDate, sequence, title, type);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return dto;
	}

	public int updateTodo(long id, String type) {
		int updateData = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String typo = null;
		if (type.equals("TODO")) {
			typo = "DOING";
		} else {
			typo = "DONE";
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "UPDATE todo SET type=? where id=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, typo);
			ps.setLong(2, id);
			updateData = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return updateData;
	}

	public List<TodoDto> getTodos(String ttype) {
		List<TodoDto> todoList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "SELECT id,name,date_format(regdate, '%Y-%m-%d') regdate,sequence,title,type FROM todo WHERE type=? ORDER BY regDate desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ttype);
			rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String regDate = rs.getString("regdate");
				int sequence = rs.getInt("sequence");
				String title = rs.getString("title");
				String type = rs.getString("type");
				TodoDto dto = new TodoDto(id, name, regDate, sequence, title, type);
				todoList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return todoList;
	}

	public int addTodo(TodoDto dto) {
		int addData = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "INSERT INTO todo (title,name,sequence) values (?,?,?)";
			ps = conn.prepareStatement(sql);

			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getSequence());

			addData = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return addData;
	}
}
