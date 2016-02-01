﻿using System;

using Xgame.GamePart.Tmpl.Attr;
using Xgame.GamePart.Tmpl.Type;

namespace Xgame.GameBizModule.Hero.Tmpl
{
    /// <summary>
    /// 武将模版
    /// </summary>
    [FromXlsxFile("hero.xlsx", SheetIndex = 0)]
    public class HeroTmpl : BaseXlsxTmpl
    {
        /** Id */
        public XlsxInt _Id;
        /** 名称 */
        public XlsxStr _name;
    }
}
