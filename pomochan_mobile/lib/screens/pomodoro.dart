import 'package:flutter/material.dart';
import 'package:pomochan_mobile/screens/signUp.dart';
import 'package:pomochan_mobile/widgets/pomodoroTimer.dart';

class PomodoroPage extends StatelessWidget {
  const PomodoroPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Stack(
          children: [
            Padding(
              padding: const EdgeInsets.only(
                  top: 80.0), // Adds space for the floating button
              child: Column(
                //padding: const EdgeInsets.all(16.0),
                children: [
                  const Pomodorotimer(
                    color1: Color.fromRGBO(117, 134, 148, 1.000),
                    color2: Color.fromRGBO(224, 122, 95, 1.000),
                    textColor: Color.fromRGBO(247, 231, 220, 1),
                  ),
                  const SizedBox(height: 30), // Space between title and list
                  FilledButton(
                    onPressed: () => {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => SignUp()),
                      )
                    },
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all(
                        Color.fromRGBO(117, 134, 148, 1.000),
                      ),
                      padding: MaterialStateProperty.all(
                        const EdgeInsets.symmetric(
                            horizontal: 25, vertical: 20.0),
                      ),
                    ),
                    child: Text(
                      "Modo concentración",
                      style: TextStyle(
                        fontSize: 30.0,
                        fontWeight: FontWeight.normal,
                        letterSpacing:1,
                        color: Color.fromRGBO(247, 231, 220, 1)
                      ),
                    ),
                  )
                ],
              ),
            ),
            //ActionButton at the top-right corner
            Positioned(
              right: 16.0, // Distance from the right edge
              top: 16.0, // Distance from the top edge
              child: FloatingActionButton(
                onPressed: () {
                  print('Config button pressed');
                },
                child: const Icon(Icons.settings),
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
