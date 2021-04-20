import 'package:flutter/material.dart';

class HeaderAppBar extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return ClipPath(
      clipper: MyCustomClipper(),
      child: Container(
        height: 130,
        decoration: BoxDecoration(
          image: DecorationImage(
              fit: BoxFit.cover,
              image: AssetImage('images/tipos_arboles.webp'),
          )
        ),
        child: Center(
          child: Text(
            'Tipos de Arboles',
            style: TextStyle(
              color: Colors.white,
              fontSize: 20,
              fontWeight: FontWeight.w900
            ),
          ),
        ),
      ),
    );
  }
}

class MyCustomClipper extends CustomClipper<Path> {

  @override
  Path getClip(Size size) {
    var firstStart = Offset(size.width / 6, size.height - 40);
    var firstEnd = Offset(size.width / 2 - size.width / 6, size.height - 20);
    var secondStart = Offset(size.width / 2, size.height - 5);
    var secondEnd = Offset(size.width / 2 + size.width / 6, size.height - 20);
    var thirdStart = Offset(size.width - (size.width / 6), size.height - 40);
    var thirdEnd = Offset(size.width, size.height - 40);

    final Path path = new Path()
      ..lineTo(0, size.height - 40)
      ..quadraticBezierTo(firstStart.dx, firstStart.dy, firstEnd.dx, firstEnd.dy)
      ..quadraticBezierTo(secondStart.dx, secondStart.dy, secondEnd.dx, secondEnd.dy)
      ..quadraticBezierTo(thirdStart.dx, thirdStart.dy, thirdEnd.dx, thirdEnd.dy)
      ..lineTo(size.width, 0)
      ..close();
    return path;
  }

  @override
  bool shouldReclip(covariant CustomClipper<Path> oldClipper) => false;
}