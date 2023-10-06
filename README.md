# Padh.Le - Learning Goals and Progress Tracker

Padh.Le is an Android app designed to help students set and track their learning goals effectively. This README file provides an overview of the app's features, technologies used, and instructions for getting started.

**Visit our [landing page](https://gnipun05.github.io/Padhle-landingpage/) for more information!**

## Features

### 1. User Authentication
Padh.Le utilizes Firebase authentication to securely manage user accounts. Users can create an account or log in using their email and password, ensuring a personalized experience.

### 2. Goal Setting and Tracking
Users can create and manage their learning goals by setting realistic targets. The app provides a visual representation of their learning progress, helping them stay motivated and organized.

### 3. Task Management
Padh.Le allows users to list and categorize their tasks, both past and present. Each task can include details such as a title, description, due date, and difficulty level.

### 4. Predictive Learning
Machine learning algorithms are employed to predict when users are likely to finish their tasks. These predictions take into account the user's type (e.g., fast learner, average learner) and the difficulty level of the task.

### 5. Collaborative Sessions
Users can create or join study sessions, enabling them to collaborate with friends. Within these sessions, students can view the tasks that their friends have added and see what tasks have been completed on the current day.

## Technologies Used

- **Android**: The app is built for the Android platform, ensuring a broad user base.
- **Firebase**: Firebase is used for user authentication and data storage, providing a secure and scalable backend.
- **Machine Learning**: Custom machine learning models are implemented to predict task completion times based on user behavior and task attributes.
- **Real-time Data**: Firebase Realtime Database or Firestore can be used to sync data between users in collaborative sessions, ensuring up-to-date information sharing.
- **UI/UX**: The app is designed with a user-friendly interface to enhance the overall experience.

## Getting Started

Follow these steps to set up and run Padh.Le on your local development environment:

1. **Clone the Repository**: Clone the Padh.Le repository to your local machine.

   ```
   git clone https://github.com/yourusername/padh-le.git
   ```

2. **Install Dependencies**: Make sure you have Android Studio and the required Android SDK components installed. Open the project in Android Studio and let it install any necessary dependencies.

3. **Firebase Configuration**: Set up a Firebase project and configure it with your Android app. Update the Firebase configuration file (`google-services.json`) in the project.

4. **Machine Learning Models**: If you have trained machine learning models for task completion prediction, include them in the project and ensure they are properly integrated.

5. **Build and Run**: Build the project and run it on an Android emulator or a physical Android device.

6. **Test Accounts**: Use the Firebase authentication system to create test accounts for your app. You can also integrate Firebase AuthenticationUI for a seamless login/signup experience.

7. **Collaborative Sessions**: Test the collaborative session feature by creating and joining study sessions with multiple test accounts.

## Contributing

Contributions to Padh.Le are welcome! Feel free to open issues, suggest new features, or submit pull requests to enhance the app's functionality.

---

Enjoy using Padh.Le to set and achieve your learning goals effectively! If you have any questions or need assistance, please don't hesitate to contact us.
