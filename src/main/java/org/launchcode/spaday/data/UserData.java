package org.launchcode.spaday.data;

import org.launchcode.spaday.models.User;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oksana
 */
public class UserData {
   private static final Map<Integer, User> users = new HashMap<>();

   public static void addUser(User user){
      users.put(user.getId(), user);
   }

   public static User getById(int id){
      return users.get(id);
   }

   public static Collection<User> getAll(){
      return users.values();
   }

   public static void remove(int id){
      users.remove(id);
   }
}
