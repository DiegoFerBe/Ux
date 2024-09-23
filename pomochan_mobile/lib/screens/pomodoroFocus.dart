import 'package:flutter/material.dart';
import 'package:pomochan_mobile/widgets/pomodoroTimer.dart';

class PomodoroFocusPage extends StatelessWidget {
  const PomodoroFocusPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF405D72), // Blue background
      body: SafeArea(
        child: Stack(
          children: [
            Padding(
              padding: const EdgeInsets.only(top: 120.0), // Adds space for the floating button
              child: SingleChildScrollView( // Wrap Column with SingleChildScrollView
                child: Column(
                  children: [
                    Center(
                      child: Text(
                        'Modo concentración',
                        textAlign: TextAlign.center, // Center the text
                        style: TextStyle(
                          fontSize: 50,
                          fontWeight: FontWeight.bold,
                          color: Color.fromRGBO(247, 231, 220, 1),
                          height: 0.8,
                        ),
                      ),
                    ),
                    SizedBox(height: 20), // Add some spacing between text and timer
                    Pomodorotimer(
                      color1: Color.fromRGBO(247, 231, 220, 1),
                      color2: Color.fromRGBO(224, 122, 95, 1.000),
                      textColor: Color(0xFF405D72),
                    ),
                  ],
                ),
              ),
            ),
            // ActionButton at the top-right corner
            Positioned(
              right: 16.0, // Distance from the right edge
              top: 16.0, // Distance from the top edge
              child: FloatingActionButton(
                onPressed: () {
                  print('Config button pressed');
                },
                child: const Icon(Icons.settings),
                foregroundColor: Color(0xFF405D72),
                backgroundColor: Color.fromRGBO(247, 231, 220, 1),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
