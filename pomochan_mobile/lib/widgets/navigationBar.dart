import 'package:flutter/material.dart';

// Define a callback type to handle navigation bar taps
typedef OnTabSelected = void Function(int index);

class CustomNavigationBar extends StatelessWidget {
  final int selectedIndex;
  final OnTabSelected onTabSelected;

  const CustomNavigationBar({super.key, required this.selectedIndex, required this.onTabSelected});

  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
      items: <BottomNavigationBarItem>[
        BottomNavigationBarItem(
          icon: _buildIcon(Icons.schedule, 0),
          label: 'Alarm',
        ),
        BottomNavigationBarItem(
          icon: _buildIcon(Icons.business, 1),
          label: 'Pomodoro',
        ),
      ],
      currentIndex: selectedIndex,
      selectedItemColor: Colors.white, // Selected icon's background color
      unselectedItemColor: Colors.white70, // Unselected icon's color
      backgroundColor: Color(0xFF405D72),
      showSelectedLabels: false, // Hide labels for selected items
      showUnselectedLabels: false, // Hide labels for unselected items
      onTap: onTabSelected,
    );
  }

  // Function to build icon with a custom background for selected icons
  Widget _buildIcon(IconData iconData, int index) {
    bool isSelected = selectedIndex == index;

    return Container(
      padding: EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0), // Padding for icon
      decoration: isSelected
          ? BoxDecoration(
              color: Colors.white, // Background color for selected icon
              borderRadius: BorderRadius.circular(20.0),
            )
          : null,
      child: Icon(
        iconData,
        color: isSelected ? Color(0xFF405D72) : Colors.white70, // Icon color
      ),
    );
  }
}
