# 🕒 Spring Boot Scheduler Demo with Redis Caching and H2 Database

This is a Spring Boot application that demonstrates:

✅ Redis Caching  
✅ H2 In-Memory Database  
✅ Static Scheduled Tasks  
✅ Dynamic Scheduling using `ScheduledFuture`  
✅ `ThreadPoolTaskScheduler` Integration  
✅ Spring Filter

---

## 🚀 Features

### 🧠 Redis Caching
- Uses `@Cacheable`, `@CacheEvict`, and `@CachePut`
- Speeds up repeated reads from the database

### 🗃️ H2 In-Memory Database
- Auto-configured for quick development and testing
- Data resets on restart
- H2 Console enabled for easy viewing

### ⏱️ Static Scheduler
- Executes fixed interval tasks using `@Scheduled`

### 🔄 Dynamic Scheduler
- Create, cancel, and manage scheduled tasks at runtime
- Backed by `ThreadPoolTaskScheduler` and `ScheduledFuture<?>`

---

## 📦 Tech Stack

- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **Redis Cache**
- **Spring Scheduler**
- **Filter**
---


