import 'package:flutter/material.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../themes/index.dart';

class TAudioPlayer extends StatefulWidget {
  const TAudioPlayer({super.key});

  @override
  _TAudioPlayerState createState() => _TAudioPlayerState();
}

// TODO
class _TAudioPlayerState extends State<TAudioPlayer> {
  @override
  Widget build(BuildContext context) => SizedBox(
    height: 64,
    child: DecoratedBox(
      decoration: const BoxDecoration(
        color: Colors.black87,
        borderRadius: Sizes.borderRadiusCircular8,
      ),
      child: Row(
        children: [
          IconButton(
            icon: const Icon(Symbols.play_arrow_rounded),
            onPressed: () {},
          ),
          const Text('00:10/00:21'),
        ],
      ),
    ),
  );
}
