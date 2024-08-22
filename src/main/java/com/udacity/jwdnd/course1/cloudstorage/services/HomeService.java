package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

import java.util.List;

public interface HomeService {
    List<File> getFiles();
}
