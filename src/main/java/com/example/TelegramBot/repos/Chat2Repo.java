package com.example.TelegramBot.repos;

import com.example.TelegramBot.models.Chat2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Chat2Repo extends JpaRepository<Chat2, Long> {
}
