package org.smalldong.admin.application;

import org.smalldong.admin.domain.modle.Resource;
import org.smalldong.admin.domain.repository.ResourceRepository;
import org.smalldong.admin.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

/**
 * Created by xieqiang on 2016/9/17.
 */
@Service
@CacheConfig(cacheNames = "resource")
public class ResourceService {

    @Autowired
    protected ResourceRepository resourceRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Caching(
            put = @CachePut(key = "#resource.id"),
            evict = @CacheEvict(key = "'list'")
    )
    public Resource create(Resource resource) {
        validate(resource);
        resource.setId(UUID.randomUUID().toString());
        resourceRepository.add(resource);
        return resource;
    }

    @Caching(
            put = @CachePut(key = "#resource.id"),
            evict = @CacheEvict(key = "'list'")
    )
    public Resource modify(Resource resource) {
        validate(resource);
        resourceRepository.update(resource);
        return resource;
    }

    @Cacheable
    public Resource get(String code){
        return resourceRepository.get(code);
    }

    @Cacheable(key = "'list'")
    public List<Resource> list(){
        return resourceRepository.list();
    }

    @Caching(
            evict = {@CacheEvict(key = "#code"), @CacheEvict(key = "'list'")}
    )
    public void delete(String code){
        roleRepository.removeRoleResourceByResourceId(code);
        resourceRepository.remove(code);
    }

    @Caching(
            evict = {@CacheEvict(key = "#code"), @CacheEvict(key = "'list'")}
    )
    public void switchStatus(String code,boolean disable){
        resourceRepository.switchStatus(code,disable);
    }

    private void validate(Resource resource) {
        Assert.hasText(resource.getTitle());
        Assert.hasText(resource.getUrl());

    }
}
