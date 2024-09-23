import 'package:flutter/material.dart';
import 'package:pomochan_mobile/widgets/alarmItem.dart';

class AlarmPage extends StatelessWidget {
  const AlarmPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Stack(
          children: [
            Padding(
              padding: const EdgeInsets.only(
                  top: 80.0), // Adds space for the floating button
              child: Container(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment:
                      CrossAxisAlignment.start, // Align the title to the left
                  children: [
                    const Text(
                      'Alarmas', // Title for the alarm list
                      style: TextStyle(
                        fontSize: 25,
                        fontWeight: FontWeight.normal,

                      ),
                    ),
                    SizedBox(height: 10), // Space between title and list
                    Expanded(
                      child: ListView(
                        children: [
                          AlarmItem(
                            time: '07:00 AM',
                            isActive: true,
                            onSwitchChanged: (value) {
                              print('Alarm 1 toggled: $value');
                            },
                          ),
                          AlarmItem(
                            time: '08:00 AM',
                            isActive: false,
                            onSwitchChanged: (value) {
                              print('Alarm 2 toggled: $value');
                            },
                          ),
                          // Add more AlarmItem widgets here as needed
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ),
            // FloatingActionButton at the top-right corner
            Positioned(
              right: 16.0, // Distance from the right edge
              top: 16.0, // Distance from the top edge
              child: FloatingActionButton(
                onPressed: () {
                  print('Add button pressed');
                },
                child: const Icon(Icons.add),
                foregroundColor: Colors.white,
                backgroundColor: Color(0xFF405D72),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
