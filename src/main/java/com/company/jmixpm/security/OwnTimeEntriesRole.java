package com.company.jmixpm.security;

import com.company.jmixpm.entity.TimeEntry;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "Own time Entries", code = OwnTimeEntriesRole.CODE)
public interface OwnTimeEntriesRole {
    String CODE = "own-time-entries";

    @JpqlRowLevelPolicy(entityClass = TimeEntry.class, where = "{E}.user.id = :current_user_id")
    void timeEntry();
}