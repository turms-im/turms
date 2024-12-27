import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

import '../../../../infra/assets/assets.gen.dart';
import '../../../themes/colors.dart';

class TEmpty extends StatelessWidget {
  const TEmpty({super.key});

  @override
  Widget build(BuildContext context) => Center(
          child: Opacity(
        opacity: 0.2,
        child: ColorFiltered(
          colorFilter: AppColors.greyscale,
          child: SvgPicture.asset(
            width: 100,
            Assets.images.iconSvg,
          ),
        ),
      ));
}
