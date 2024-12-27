import 'package:flutter/cupertino.dart';
import 'package:material_symbols_icons/material_symbols_icons.dart';

class TImageBroken extends StatelessWidget {
  const TImageBroken({super.key});

  @override
  Widget build(BuildContext context) => const DecoratedBox(
        decoration: BoxDecoration(color: Color.fromARGB(255, 244, 244, 244)),
        child: Icon(
          Symbols.image_not_supported_rounded,
          color: Color.fromARGB(255, 82, 82, 82),
        ),
      );
}
