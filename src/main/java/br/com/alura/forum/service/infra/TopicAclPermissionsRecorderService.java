package br.com.alura.forum.service.infra;

import br.com.alura.forum.model.topic.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TopicAclPermissionsRecorderService {

    @Autowired
    private MutableAclService aclService;

    public void addPermissions(UserDetails principal, Topic topic, Permission... permissions) {

        ObjectIdentity identity = new ObjectIdentityImpl(topic);
        MutableAcl acl = aclService.createAcl(identity);

        PrincipalSid principalSid = new PrincipalSid(principal.getUsername());

        for (Permission permission : permissions) {
            acl.insertAce(acl.getEntries().size(), permission, principalSid, true);
            enterPermissionForAdmins(acl, permission);
        }

        aclService.updateAcl(acl);
    }

    private void enterPermissionForAdmins(MutableAcl acl, Permission permission) {
        GrantedAuthoritySid adminsSid = new GrantedAuthoritySid("ROLE_ADMIN");
        acl.insertAce(acl.getEntries().size(), permission, adminsSid,  true);
    }

}
