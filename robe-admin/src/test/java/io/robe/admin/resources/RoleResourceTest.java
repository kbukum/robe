package io.robe.admin.resources;

import io.robe.admin.hibernate.entity.Role;
import org.junit.Assert;

/**
 * Created by hasanmumin on 03/10/16.
 */
public class RoleResourceTest extends BaseResourceTest<Role> {

    @Override
    public String getPath() {
        return "roles";
    }

    @Override
    public Class<Role> getClazz() {
        return Role.class;
    }

    @Override
    public void assertEquals(Role model, Role response) {
        Assert.assertEquals(model.getName(), response.getName());
        Assert.assertEquals(model.getCode(), response.getCode());
    }

    @Override
    public void assertEquals(Role mergeInstance, Role original, Role response) {
        Assert.assertEquals(mergeInstance.getName(), response.getName());
        Assert.assertEquals(original.getCode(), response.getCode());
    }

    @Override
    public Role instance() {

        Role role = new Role();
        role.setCode("CODE");
        role.setName("Name");
        return role;
    }

    @Override
    public Role update(Role response) {
        response.setName("Name-1");
        return response;
    }

    @Override
    public Role mergeInstance() {

        Role role = new Role();
        role.setName("Name-2");
        return role;
    }
}
