import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart'; // Asegúrate de importar flutter_svg

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
          icon: _buildSvgIcon('assets/icons/tomato.svg', 1), // Usar la función para el SVG
          label: 'Pomodoro',
        ),
      ],
      currentIndex: selectedIndex,
      selectedItemColor: Colors.white, // Color de fondo del ícono seleccionado
      unselectedItemColor: Colors.white70, // Color del ícono no seleccionado
      backgroundColor: Color(0xFF405D72),
      showSelectedLabels: false, // Ocultar etiquetas para elementos seleccionados
      showUnselectedLabels: false, // Ocultar etiquetas para elementos no seleccionados
      onTap: onTabSelected,
    );
  }

  // Función para construir ícono con fondo personalizado para íconos seleccionados
  Widget _buildIcon(IconData iconData, int index) {
    bool isSelected = selectedIndex == index;

    return Container(
      padding: EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0), // Padding para el ícono
      decoration: isSelected
          ? BoxDecoration(
              color: Colors.white, // Color de fondo para el ícono seleccionado
              borderRadius: BorderRadius.circular(20.0),
            )
          : null,
      child: Icon(
        iconData,
        color: isSelected ? Color(0xFF405D72) : Colors.white70, // Color del ícono
      ),
    );
  }

  // Nueva función para construir íconos SVG con un fondo personalizado
  Widget _buildSvgIcon(String assetPath, int index) {
    bool isSelected = selectedIndex == index;

    return Container(
      padding: EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0), // Padding para el ícono
      decoration: isSelected
          ? BoxDecoration(
              color: Colors.white, // Color de fondo para el ícono seleccionado
              borderRadius: BorderRadius.circular(20.0),
            )
          : null,
      child: SvgPicture.asset(
        assetPath,
        width: 24, // Ajusta el tamaño del SVG
        height: 24,
        color: isSelected ? Color(0xFF405D72) : Colors.white70, // Color del ícono SVG
      ),
    );
  }
}
