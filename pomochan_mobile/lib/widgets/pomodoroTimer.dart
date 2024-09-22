import 'package:flutter/material.dart';

class Pomodorotimer extends StatefulWidget {
  final Color color1; // Color when not pressed
  final Color color2; // Color when pressed
  final Color textColor;

  const Pomodorotimer({
    super.key,
    required this.color1,
    required this.color2,
    required this.textColor
  });

  @override
  _PomodorotimerState createState() => _PomodorotimerState();
}

class _PomodorotimerState extends State<Pomodorotimer> {
  bool _isPressed = false; // State to track the button press

  void _toggleButton() {
    setState(() {
      _isPressed = !_isPressed; // Toggle between color1 and color2
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: GestureDetector(
        onTap: _toggleButton,
        child: AnimatedContainer(
          duration: const Duration(milliseconds: 300),
          width: 350, // Diameter of the button
          height: 400,
          decoration: BoxDecoration(
            color: _isPressed ? widget.color2 : widget.color1, // Use colors from parent
            shape: BoxShape.circle,
            boxShadow: [
              BoxShadow(
                color: Colors.black.withOpacity(0.2),
                spreadRadius: 5,
                blurRadius: 10,
                offset: const Offset(0, 5), // Shadow effect
              ),
            ],
          ),
          child:  Center(
            child: Text(
              _isPressed ? "25:00 min" : "Iniciar",
              style: TextStyle(
                color: widget.textColor,
                fontSize: 50,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
        ),
      ),
    );
  }
}

