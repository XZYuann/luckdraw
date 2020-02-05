package com.example.demo;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface luckymoneyRepository extends JpaRepository<luckymoney,Integer> {
}
