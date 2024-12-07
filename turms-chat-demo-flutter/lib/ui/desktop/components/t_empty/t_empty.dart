import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

import '../../../../infra/assets/assets.gen.dart';

class TEmpty extends StatelessWidget {
  const TEmpty({super.key});

  @override
  Widget build(BuildContext context) => Center(
          child: Opacity(
        opacity: 0.2,
        child: ColorFiltered(
          //  greyscale color filter
          colorFilter: const ColorFilter.matrix(<double>[
            0.2126, 0.7152, 0.0722, 0, 0, //
            0.2126, 0.7152, 0.0722, 0, 0,
            0.2126, 0.7152, 0.0722, 0, 0,
            0, 0, 0, 1, 0,
          ]),
          child: SvgPicture.asset(
            width: 100,
            Assets.images.iconSvg,
          ),
        ),
      ));
}
