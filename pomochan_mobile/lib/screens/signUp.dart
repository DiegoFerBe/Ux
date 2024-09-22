import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:pomochan_mobile/screens/pomodoroFocus.dart';

class SignUp extends StatefulWidget {
  SignUp({super.key});

  @override
  State<SignUp> createState() => _SignUpState();
}

class _SignUpState extends State<SignUp> {
  final List<TextEditingController> _controllers =
      List.generate(4, (index) => TextEditingController());
  final List<FocusNode> _focusNodes = List.generate(4, (index) => FocusNode());
  final List<String> _inputValues = List.filled(4, ''); // Track input values

  @override
  void dispose() {
    // Clean up controllers and focus nodes when the widget is disposed
    for (var controller in _controllers) {
      controller.dispose();
    }
    for (var focusNode in _focusNodes) {
      focusNode.dispose();
    }
    super.dispose();
  }

  void _checkCodeCompletion() {
    bool allFilled = _controllers.every((controller) => controller.text.isNotEmpty);
    if (allFilled) {
      print('All 4 digits have been entered: ${_controllers.map((c) => c.text).join()}');
      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => const PomodoroFocusPage()),
                      );
    }
  }

  @override
  Widget build(BuildContext context) {
    // Check if the keyboard is visible
    bool isKeyboardVisible = MediaQuery.of(context).viewInsets.bottom > 0;

    return Scaffold(
      backgroundColor: const Color(0xFF405D72), // Blue background
      body: SingleChildScrollView( // Wrap in SingleChildScrollView
        padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 100.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            const SizedBox(height: 35), // Top spacing
            const Center(
              child: Text(
                'Modo concentración',
                textAlign: TextAlign.center, // Center the text
                style: TextStyle(
                  fontSize: 50,
                  fontWeight: FontWeight.bold,
                  height: 0.8,
                  color: Colors.white, // White title color
                ),
              ),
            ),
            const SizedBox(height: 200), // Space between title and input
            Center(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: List.generate(4, (index) {
                  return SizedBox(
                    width: 40, // Adjust width for each box
                    child: TextField(
                      controller: _controllers[index],
                      obscureText: true, // Hide text
                      focusNode: _focusNodes[index], // Assign focus node
                      keyboardType: TextInputType.number, // Numeric keyboard
                      maxLength: 1, // One digit per box
                      textAlign: TextAlign.center,
                      style: const TextStyle(
                        fontSize: 24,
                        color: Colors.white,
                      ),
                      inputFormatters: [
                        FilteringTextInputFormatter.digitsOnly, // Only numbers
                      ],
                      decoration: InputDecoration(
                        counterText: "", // Hide character counter
                        enabledBorder: const UnderlineInputBorder(
                          borderSide: BorderSide(
                            color: Colors.white,
                            width: 2.0, // Line thickness
                          ),
                        ),
                        focusedBorder: const UnderlineInputBorder(
                          borderSide: BorderSide(
                            color: Colors.white, // Line color when focused
                            width: 3.0, // Thicker line
                          ),
                        ),
                      ),
                      onChanged: (value) {
                        _inputValues[index] = value; // Update input value
                        if (value.isNotEmpty && index < 3) {
                          // Move focus to the next text field
                          FocusScope.of(context).requestFocus(_focusNodes[index + 1]);
                        }
                        if (value.isEmpty && index > 0) {
                          // Move focus to the previous text field if backspacing
                          FocusScope.of(context).requestFocus(_focusNodes[index - 1]);
                        }
                        _checkCodeCompletion(); // Check if all fields are filled
                      },
                    ),
                  );
                }),
              ),
            ),
            const SizedBox(height: 100), // Space between input and icon
            // Asterisks display
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: List.generate(4, (index) {
                return Text(
                  _inputValues[index].isNotEmpty ? '*' : '', // Show asterisk if filled
                  style: const TextStyle(
                    fontSize: 24,
                    color: Colors.white,
                  ),
                );
              }),
            ),
            // Show fingerprint icon only when keyboard is not visible
            if (!isKeyboardVisible)
              Align(
                alignment: Alignment.bottomCenter,
                child: IconButton(
                  icon: const Icon(Icons.fingerprint),
                  color: Colors.white,
                  iconSize: 70,
                  onPressed: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => const PomodoroFocusPage()),
                      );
                  },
                ),
              ),
          ],
        ),
      ),
    );
  }
}
