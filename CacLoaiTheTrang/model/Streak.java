/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CacLoaiTheTrang.model;

import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Streak {
    private int streakId;
    private int userId;
    private LocalDate startDate;
    private LocalDate lastDate;
    private int streak;

    public Streak() {
    }

    public Streak(int streakId, int userId, LocalDate startDate, LocalDate lastDate, int streak) {
        this.streakId = streakId;
        this.userId = userId;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.streak = streak;
    }

    public int getStreakId() {
        return streakId;
    }

    public void setStreakId(int streakId) {
        this.streakId = streakId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
    
    
}
