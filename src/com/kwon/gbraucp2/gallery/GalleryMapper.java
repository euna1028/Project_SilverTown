package com.kwon.gbraucp2.gallery;

import java.util.List;

public interface GalleryMapper {
	public abstract int delete(GalleryImage gi);

	public abstract List<GalleryImage> get();

	public abstract String getFile(GalleryImage gi);

	public abstract int upload(GalleryImage gi);
}
