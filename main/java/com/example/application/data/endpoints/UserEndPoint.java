package com.example.application.data.endpoints;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import dev.hilla.Endpoint;
import jakarta.servlet.http.HttpSession;
import com.example.application.data.entity.Match;
import com.example.application.data.service.MatchService;
import jakarta.validation.constraints.NotNull;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@BrowserCallable
@AnonymousAllowed
@Endpoint
public class UserEndPoint {


    

    @Autowired
    private UserService userService;
    
    @Autowired
    private MatchService matchService;

    @Autowired
    private HttpSession session;
    public void setUserHobbies(String account ,UserService.Userhobbies hobbies ){
        userService.setUserHobbies(account,hobbies);
    } 

    public UserService.UserRecord register( UserService.UserRecord userRecord){
         return userService.registerUser(
                userRecord.account(),
                userRecord.password(),
                userRecord.username()
                );
    }

    public UserService.UserRecord login(String account, String password) {
        return userService.login(account, password, session);
    }

    public Optional<User> getUserByAccount(String account) {
        return userService.findByAccount(account);
    }

    public void logout() {
        userService.logout(session);
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }
    public String getCurrentUser() { //account
         User Currentuser =userService.getCurrentUser(session);
         return Currentuser.getAccount();
    }

    // 當用戶點擊"開始配對"後使用此function
    // 可能返回 Match或 null (通常是 match，null則代表完全找不到適合的人)
    // 若返回 Match，則給用戶看此 match的資訊(日期、配到的人的資料)
    // 有一個 .getOtherUser(User user)可以用，在 Match.java裡，把 currentUser當參數就能找到在此 match 中的另一人
    public Match findOrCreateMatchForUser(User currentUser) {
        return matchService.findOrCreateMatchForUser(currentUser);
    }

    // 當用戶點擊"同意配對"後使用此 function
    public void acceptMatch(User user){
        user.setAcceptMatch(true);
    }
    // 當用戶點擊"拒絕配對"後使用此 function
    public void refuseMatch(User user){
        user.setAcceptMatch(false);
    }
    

    public void updateUserGender(User user, String gender) {
        user.setGender(gender);
    }

    public void updateUserSports(User user, String sport, boolean isAdd) {
        if (isAdd) {
            user.addSport(sport);
        } else {
            user.removeSport(sport);
        }
    }

    public void updateUserMovies(User user, String movie, boolean isAdd) {
        if (isAdd) {
            user.addMovie(movie);
        } else {
            user.removeMovie(movie);
        }
    }

    public void updateUserFoods(User user, String food, boolean isAdd) {
        if (isAdd) {
            user.addFood(food);
        } else {
            user.removeFood(food);
        }
    }

    public void updateUserMbti(User user, String mbti) {
        user.setMbti(mbti);
    }
}
