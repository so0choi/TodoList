package todo.dto;

public class TodoDto {

	private long id;
	private String name;
	private String regDate;
	private int sequence;
	private String title;
	private Type type;

	public TodoDto() {

	}

	public TodoDto(String title, String name, int sequence) {
		this.title = title;
		this.name = name;
		this.sequence = sequence;
	}

	public TodoDto(long id, String name, String regDate, int sequence, String title, Type type) {
		this.id = id;
		this.name = name;
		this.regDate = regDate;
		this.sequence = sequence;
		this.title = title;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRegDate() {
		return regDate;
	}

	public int getSequence() {
		return sequence;
	}

	public String getTitle() {
		return title;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", name=" + name + ", regDate=" + regDate + ", sequence=" + sequence + ", title="
				+ title + ", type=" + type + "]";
	}

}
