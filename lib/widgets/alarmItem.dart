import 'package:flutter/material.dart';

class AlarmItem extends StatefulWidget {
  final String time;
  final bool isActive;
  final ValueChanged<bool> onSwitchChanged;

  const AlarmItem({
    super.key,
    required this.time,
    required this.isActive,
    required this.onSwitchChanged,
  });

  @override
  _AlarmItemState createState() => _AlarmItemState();
}

class _AlarmItemState extends State<AlarmItem> {
  late bool _isActive;

  @override
  void initState() {
    super.initState();
    _isActive = widget.isActive;
  }

  void _handleSwitchChanged(bool value) {
    setState(() {
      _isActive = value;
    });
    widget.onSwitchChanged(value);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 35, vertical: 25),
      margin: const EdgeInsets.symmetric(vertical: 4.0), // Space between items
      decoration: BoxDecoration(
        color: Color.fromRGBO(117, 134, 148, 1.000), // Background color for the container
        borderRadius: BorderRadius.circular(20.0), // Rounded corners
        boxShadow: [
          BoxShadow(
            color: Colors.grey.withOpacity(0.2),
            spreadRadius: 1,
            blurRadius: 5,
            offset: Offset(0, 3), // Shadow position
          ),
        ],
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 30.0, vertical: 15),
            decoration: BoxDecoration(
              color: Color.fromRGBO(247, 231, 220, 1), // Background color for the time container
              borderRadius: BorderRadius.circular(30.0), // Rounded corners for the time container
            ),
            child: Text(
              widget.time,
              style: const TextStyle(
                fontSize: 18.0,
                fontWeight: FontWeight.bold,
                color: Color.fromRGBO(108, 107, 107, 1.000),
              ),
            ),
          ),
          SwitchTheme(
            data: SwitchThemeData(
              thumbColor: MaterialStateProperty.all(Color.fromRGBO(247, 231, 220, 1.000)), // Thumb color when active
              trackColor: MaterialStateProperty.all(Color.fromRGBO(64, 93, 114, 1.000)), // Track color when inactive
              overlayColor: MaterialStateProperty.all(Colors.transparent), // Color of the touch feedback overlay
            ),
            child: Switch(
              value: _isActive,
              onChanged: _handleSwitchChanged,
            ),
          ),
        ],
      ),
    );
  }
}
