package sb.test.common.pojo;

import lombok.Data;

/**
 * @author: LengShui on 2022-08-09 11:19
 **/
@Data
public class User {
	public String jobType = "";

	public Long STARTING = 0L;

	public Long RUNNING = 0L;

	public Long STOPPING = 0L;

	public Long FAILED = 0L;

	public Long STOPPED = 0L;

	public Long FINISHED = 0L;
}
