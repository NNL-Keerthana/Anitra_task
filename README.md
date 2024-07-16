# AnitraTask

AnitraTask is an Android application that integrates Google Maps to provide location-based functionality. Users can view their current location, place markers on the map, and see the latitude and longitude of the points they mark. A clear button allows users to remove all markers from the map.

## Features

- **Current Location Display**: The app displays the user's current location on the map.
- **Marker Placement**: Users can place markers on the map by long-pressing any location.
- **Location Coordinates Display**: The latitude and longitude of the marked location will be displayed on the screen.
- **Clear Markers**: A button to clear all markers except the current location marker.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/Satyam12singh/Anitra_task.git
    ```
2. Open the project in Android Studio.
3. Build the project and run it on an Android device or emulator.

## Usage

1. **Main Activity**: 
    - The app starts with the `MainActivity`.
    - Click the "Map" button to navigate to the `MapsActivity`.

2. **Maps Activity**:
    - The map is displayed with the user's current location.
    - Long-press on any location on the map to place a marker.
    - The latitude and longitude of the marker will be displayed on the screen.
    - Click the "Clear Locations" button to remove all markers.

## Permissions

The app requires the following permissions:
- `ACCESS_FINE_LOCATION`
- `ACCESS_COARSE_LOCATION`

Ensure to grant these permissions when prompted.

## Code Structure

- **MainActivity.kt**: Contains the main screen with a button to navigate to the map. ([View Code](https://github.com/Satyam12singh/Anitra_task/blob/master/app/src/main/java/com/example/anitratask/MainActivity.kt))
- **MapsActivity.kt**: Handles the map display, marker placement, and location updates. ([View Code](https://github.com/Satyam12singh/Anitra_task/blob/master/app/src/main/java/com/example/anitratask/MapsActivity.kt))
- **ActivityMainBinding.kt**: Used for view binding in the Main Activity.
- **ActivityMainBinding.kt**: Used for view binding in the Main Activity.
- **ActivityMapsBinding.kt**: Used for view binding in the Maps Activity.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [Google Maps API](https://developers.google.com/maps/documentation/android-sdk)
- [Android Developers](https://developer.android.com/)


