package com.orchid.orchidbe.services.IService;

import com.orchid.orchidbe.dto.CategoryDTO;
import com.orchid.orchidbe.pojos.Category;
import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    Category getById(String id);
    void save(CategoryDTO.CategoryReq category);
    void update(String id, CategoryDTO.CategoryReq category);
    void delete(String id);


}
