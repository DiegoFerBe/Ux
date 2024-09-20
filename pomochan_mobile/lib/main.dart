import 'package:flutter/material.dart';
import 'package:pomochan_mobile/screens/alarm.dart';
import 'package:pomochan_mobile/screens/pomodoro.dart';
import 'package:pomochan_mobile/widgets/navigationBar.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'PomoChan',
      theme: ThemeData(
        primaryColor: Colors.blue, // Primary Color (Blue)
        scaffoldBackgroundColor: Color.fromARGB(255, 255, 255, 255),// Background Color (Light Gray)
        textTheme: const TextTheme(
          bodyLarge: TextStyle(color: Color.fromARGB(255, 240, 240, 240)),
         ),
       ), // Text Color (Dark Blue)
      home: const PomoChan(),
    );
  }
}

class PomoChan extends StatefulWidget{
  const PomoChan({super.key});

  @override
  State<PomoChan> createState() => _PomoChanState();
}

class _PomoChanState extends State<PomoChan> {
    int _selectedIndex = 0; // Track the selected page

     // Function to handle bottom navigation taps
  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }
    // List of widgets for each page
  static final List<Widget> _pages = <Widget>[
    const AlarmPage(),
    const PomodoroPage()
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _pages[_selectedIndex],
      bottomNavigationBar: CustomNavigationBar(
        selectedIndex: _selectedIndex,
        onTabSelected: _onItemTapped,
      ),
    );
}
}

