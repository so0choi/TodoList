package todo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import todo.dto.TodoDto;
import todo.dto.Type;

public class TodoDao {

	private static final String PROPERTIES_FILE = "info.properties";
	private Properties props;

	public Connection connectDB() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.out.println("데이터베이스 연결에 오류가 발생했습니다.");
		}
		props = new Properties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE));
		} catch (IOException e) {
			System.out.println("Properties File Load Fail");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(props.getProperty("dburl"), props.getProperty("dbUser"),
					props.getProperty("dbpasswd"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("지정한 db user 데이터로 접속하는데 오류가 생겼습니다.");
		}
		return conn;
	}

	public TodoDto getOneById(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TodoDto dto = null;
		conn = connectDB();
		String sql = "SELECT name,date_format(regdate, '%Y-%m-%d') regdate,sequence,title,type FROM todo WHERE id="
				+ id;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String regDate = rs.getString("regdate");
				int sequence = rs.getInt("sequence");
				String title = rs.getString("title");
				Type type = Type.valueOf(rs.getString("type"));
				dto = new TodoDto(id, name, regDate, sequence, title, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql문 실행에 오류가 발생했습니다.");
		}

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
		return dto;

	}

	public int updateTodo(long id, Type targetType) {
		int updateData = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		Type newType = null;
		if (targetType == Type.TODO) {
			newType = Type.DOING;
		} else {
			newType = Type.DONE;
		}
		try {
			conn = connectDB();
			String sql = "UPDATE todo SET type=? where id=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, newType.toString());
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

	public List<TodoDto> getListByType(String ttype) {
		List<TodoDto> todoList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = connectDB();
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
				Type type = Type.valueOf(rs.getString("type"));
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
			conn = connectDB();
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
