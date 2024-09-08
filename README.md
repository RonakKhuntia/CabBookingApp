<h1>Backend System of Cab Booking Application Built Using Spring Boot, PostgreSQL, OSRM-API, PostGIS</h1>

This project is a backend application for a ride-hailing service, similar to Uber. It provides functionality for users to sign up, login, book rides, track drivers, and make payments.

### Features

The application features:

* **User Authentication:** Secure user registration and login using JWT authentication.
* **Ride Booking:** Users can request rides with pick-up and drop-off locations, specifying their desired vehicle type.
* **Driver Management:** Drivers can register, manage their profiles, and receive ride requests.
* **Ride Tracking:** Users can track their driver's location in real-time.
* **Payment Processing:** Supports multiple payment methods, including cash and online payments.
* **Rating System:** Users can rate their drivers after completing a ride.
* **Surge Pricing:** Dynamically adjusts ride fares based on demand.
* **Driver Matching:** Matches riders with available drivers using strategies like nearest driver or highest-rated driver.

### Installation

1. **Prerequisites:**
    * Java Development Kit (JDK) 21 
    * Maven 3.6.3 or higher
2. **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/uber-clone.git
    ```
3. **Navigate to the project directory:**
    ```bash
    cd uber-clone
    ```
4. **Build the project:**
    ```bash
    mvn clean install
    ```

### Usage

1. **Start the application:**
    ```bash
    mvn spring-boot:run
    ```
2. **Access the API:**
    The application exposes REST APIs for interacting with various functionalities. Refer to the API documentation for endpoints and request parameters.
    * **Authentication:**
        * `/auth/signup` - Register a new user
        * `/auth/login` - Login with existing credentials
    * **Riders:**
        * `/riders/request-ride` - Request a new ride
        * `/riders/track-ride` - Track the assigned driver
    * **Drivers:**
        * `/drivers/onboard` - Onboard as a driver
        * `/drivers/accept-ride` - Accept a ride request
    * **Payments:**
        * `/payments/process` - Process payments for completed rides

### API Documentation

You can find detailed API documentation with examples in the `src/main/resources/swagger/swagger.yaml` file.

### Contributions

Contributions are welcome! Please open an issue or pull request for any bug fixes, feature additions, or improvements.
