# OOP-Arkanoid
## A-PIE Overflow

---
Group 2 - Class INT_2204_11
1. Nguyễn Dương Minh
2. Phạm Hoài Nam
3. Hoàng Trọng Nhật Linh
4. Trần Nguyễn Hoàng    
   **Instructor:** Kiều Văn Tuyên  
   **Semester:** HK1 - 2025-2026
## Description

---
This is a classic Arkanoid game developed in Java as a final project for Object-Oriented Programming course. The project demonstrates the implementation of OOP principles and design patterns.  
**Key feature**
1. Tựa game được phát triển bằng Java 17+ với JavaFX/Swing for GUI.
2. Thực hiện các nguyên tắc OOP cốt lõi: Đóng gói, Kế thừa, Đa hình và Trừu tượng.
3. Sử dụng các design patterns: Singleton, Factory Method, etc.
4. Tính năng đa luồng để game vẫn hoạt động mượt mà và UI đáp ứng người dùng
5. Bao gồm sound effects, animations, and power-up systems.
6. Hỗ trợ lưu high score

**Game mechanics**
1. Sử dụng paddle để đẩy bóng phá gạch.
2. Thu thập các power-up để có các hiệu ứng đặc biệt.
3. Nhiều level với độ khó và giao diện khác nhau.
4. Lưu score màn chơi và high score.

## UML Diagram

---
Class Diagram  
![Arkanoid_Class_Diagram-0.png](Project/src/main/resources/com/Arkanoid/img/Arkanoid_Class_Diagram-0.png)

## Design Patterns Implementation

---
1. **Singleton Pattern**  
   Used in: SoundManager, SceneCache.
2. **Template Method Pattern**  
   Used in: PowerUp, CheckCollision
3. **Facade Pattern**  
   Used in: SceneNavigator.

## Multithreading Implementation

---

**GameEngine:** Điều phối và xử lý các logic song song, bao gồm 3 luồng va chạm rồi đông bộ bằng UI  
**SoundManager:** Phương thức playBackgroundMusic tạo một thread để play tránh blocking luồng UI khi tải và khởi động nền

## Installation

---  
1. Clone the project from the repository.
2. Open the project in the IDE.
3. Run the project.

## Usage

---
**Controls**

|  Key  |       Action       |
|:-----:|:------------------:|
|   A   |  Move paddle left  |
|   D   | Move paddle right  |
| SPACE |    Launch ball     |

**How to Play**
1. **Start the game**: Click "Start" from the Main Menu.
2. **Control the paddle**: Use A/D to move left and right.
3. **Launch the ball**: Press SPACE to launch the ball from the paddle.
4. **Destroy bricks**: Bounce the ball to hit and destroy bricks.
5. **Collect power-ups**: Catch falling power-ups for special abilities.
6. **Avoid losing the ball**: Keep the ball from falling below the paddle.
7. **Complete the level**: Destroy all destructible bricks to advance.

**Power-ups**

| Name          | Effect                | Icon |
|---------------|-----------------------|------|
| FastBall      | Boost ball speed      | ![FastBall](Project/src/main/resources/com/Arkanoid/img/FastBall.png) |
| FastPaddle    | Boost paddle speed    | ![FastPaddle](Project/src/main/resources/com/Arkanoid/img/FastPaddle.png) |
| MultiBall     | Spawns 2 additional balls | ![MultiBall](Project/src/main/resources/com/Arkanoid/img/MultiBall.png) |
| SlowBall      | Decrease ball speed   | ![SlowBall](Project/src/main/resources/com/Arkanoid/img/SlowBall.png) |
| SlowPaddle    | Decrease paddle speed | ![SlowPaddle](Project/src/main/resources/com/Arkanoid/img/SlowPaddle.png) |
| RandomPowerUp | Random effect         | ![RandomPowerUp](Project/src/main/resources/com/Arkanoid/img/RandomPowerUp.png) |
| ExtraLife     | Gain  extra life      |![ExtraLife.png](Project/src/main/resources/com/Arkanoid/img/ExtraLife.png) |
| PointBonus    | Gain extra points     |![PointBonus.png](Project/src/main/resources/com/Arkanoid/img/PointBonus.png)  |
|

**Scoring System**
- Green Bricks: 50
- Yellow Bricks: 60
- Orange Bricks: 70
- Red Bricks: 80
- PowerUp: Random from 0 to 100
- Hard Bricks: 80


## Demo

---

### Screenshots

**Main Menu**

![Menu.jpg](Project/src/main/resources/com/Arkanoid/img/Menu.jpg)

**Gameplay**

**Highscore**

![High_Score.jpg](Project/src/main/resources/com/Arkanoid/img/High_Score.jpg)

**Video**

## Future Improvements

---
### Planned Features
1. Thêm các chế độ chơi mới
    - Chế độ giới hạn thời gian.
    - Chế độ nhiều người chơi kết hợp.
2. Enhanced gameplay
    - Đánh boss ở level cuối cùng.
    - Nhiều power-up đa dạng hơn(shield,etc.)
    - Hệ thống thành tựu.
3. Technical improvements
    - Đối đầu với AI(bot).
    - Chế độ nhiều người chơi.
    - Chế độ online

## Technologies Used

---


| Technology | Version | Purpose |
|:-----------:|:-------:|:--------|
| **Java**     |   17+   | Core language |
| **JavaFX**   | 19.0.2  | GUI framework |
| **Maven**    |  3.8.7  | Build tool |


## License

---
Project được phát triển dành cho học tập và không có giá trị về thương mại.

**Academic Integrity**: Code này được cung cấp như một tài liệu tham khảo tuân theo các chính sách trong học tập của tổ chức của bạn.

## Notes

---
1. Trò chơi được phát triển làm bài tập lớn phục vụ cho bộ môn OOP sử dụng ngôn ngữ Java.
2. Code được viết bởi thành viên trong nhóm.
3. The project áp dụng các design pattern và khái niệm trong OOP.

---
_Last updated: 12/11/2025_














