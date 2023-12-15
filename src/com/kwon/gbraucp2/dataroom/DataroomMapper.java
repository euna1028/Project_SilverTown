package com.kwon.gbraucp2.dataroom;

import java.util.List;

public interface DataroomMapper {
	public abstract int delete(DataroomFile df);

	public abstract List<DataroomFile> get(DataroomFile df);

	public abstract String getFile(DataroomFile df);

	public abstract int upload(DataroomFile df);
}
