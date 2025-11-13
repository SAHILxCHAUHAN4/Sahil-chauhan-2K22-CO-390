
# Boostly — boost morale, one kudos at a time

Boostly is a Spring Boot application that enables college students to recognize their peers, allocate monthly credits, and redeem earned rewards.

## 🚀 Setup Instructions

1.  **Clone the repository:**
    ```bash
    git clone [your-repository-url]
    cd your-repository
    ```
2.  **Open in STS (Spring Tool Suite):**
    * Go to `File > Import...`.
    * Select `Maven > Existing Maven Projects`.
    * Browse to the `your-repository` directory and select it.
    * Click `Finish`.
3.  **Build Project:**
    * Maven will automatically download all required dependencies (Spring Web, JPA, H2, Lombok).
    * If you don't have Lombok configured in your IDE, you may need to install the Lombok plugin.

## ▶️ Run Instructions

1.  Locate the `BoostlyApplication.java` file in the `src/main/java/com/boostly/boostly` package.
2.  Right-click the file and select `Run As > Java Application`.
3.  The application will start on the embedded Tomcat server, typically on port `8080`.

You can access the H2 in-memory database console in your browser to inspect the data:
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:boostlydb`
* **Username:** `sa`
* **Password:** `password`

---

## Endpoints Documentation

The base URL for all endpoints is `http://localhost:8080`.

### 🧑‍🎓 Students

#### `POST /api/students`
Creates a new student.
* **Body (JSON):**
    ```json
    {
      "name": "Alice Smith",
      "email": "alice@college.edu"
    }
    ```
* **Success Response (201 CREATED):**
    ```json
    {
      "id": 1,
      "email": "alice@college.edu",
      "name": "Alice Smith",
      "sendingBalance": 100,
      "redeemableBalance": 0,
      "totalCreditsReceived": 0
    }
    ```

#### `GET /api/students/{id}`
Retrieves details for a specific student.
* **Example URL:** `http://localhost:8080/api/students/1`
* **Success Response (200 OK):** (Same as POST response)

### ✨ Recognition

#### `POST /api/recognitions`
Allows one student to send credits to another.
* **Body (JSON):**
    ```json
    {
      "senderId": 1,
      "receiverId": 2,
      "credits": 25,
      "message": "Great job on the class presentation!"
    }
    ```
* **Success Response (201 CREATED):**
    ```json
    {
      "id": 1,
      "sender": { "id": 1, ... },
      "receiver": { "id": 2, ... },
      "credits": 25,
      "message": "Great job on the class presentation!",
      "timestamp": "2025-11-13T07:30:00Z"
    }
    ```

### 👍 Endorsements

#### `POST /api/endorsements`
Allows a student to endorse (like) an existing recognition.
* **Body (JSON):**
    ```json
    {
      "endorserId": 3,
      "recognitionId": 1
    }
    ```
* **Success Response (201 CREATED):**
    ```json
    {
      "id": 1,
      "endorser": { "id": 3, ... },
      "recognition": { "id": 1, ... }
    }
    ```

### 🎁 Redemption

#### `POST /api/redemptions`
Allows a student to redeem their *received* credits for a voucher. (Rate: ₹5 per credit)
* **Body (JSON):**
    ```json
    {
      "studentId": 2,
      "creditsToRedeem": 15
    }
    ```
* **Success Response (201 CREATED):**
    ```json
    {
      "message": "Redemption successful!",
      "voucherCode": "BOOSTLY-A8B4C1D9",
      "amountRedeemed": 75,
      "newRedeemableBalance": 10
    }
    ```

### 🏆 Leaderboard

#### `GET /api/leaderboard`
Gets the list of top students ranked by `totalCreditsReceived`.
* **Example URL:** `http://localhost:8080/api/leaderboard?limit=5`
* **Success Response (200 OK):**
    ```json
    [
      {
        "studentId": 2,
        "name": "Bob Johnson",
        "totalCreditsReceived": 25,
        "recognitionsReceivedCount": 1,
        "endorsementsReceivedCount": 1
      },
      {
        "studentId": 4,
        "name": "David Lee",
        "totalCreditsReceived": 10,
        "recognitionsReceivedCount": 1,
        "endorsementsReceivedCount": 0
      }
    ]
    ```
