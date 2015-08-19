**��ʼ�� XlsxTmplServ**

1������ѡ������ D �� Temp_Test Ŀ¼�µ� building.xlsx �ļ���

2������ѡ����ϵͳ��̬���ɵ� JAVA ��Դ�뱣�浽 D �� Temp_Test Ŀ¼�µ� Debug Ŀ¼��

3������ѡ�����û������� lang = zh_CN��

```
XlsxTmplServ.OBJ._xlsxFileDir = "/D:/Temp_Test/building.xlsx";
XlsxTmplServ.OBJ._debugClazzToDir = "/D:/Temp_Test/Debug";
XlsxTmplServ.OBJ._propMap = new HashMap<>();
XlsxTmplServ.OBJ._propMap.put("lang", "zh_CN");
```

----

**ע��ģ����**

1������ BuildingTmpl �ࣻ

2����֤����ģ���ࣻ

```
// ģ��������
Class<?>[] tmplClazzArr = {
    BuildingTmpl.class, 
    Shop.class,
    SysLangTmpl.class,
};

for (Class<?> tmplClazz : tmplClazzArr) {
    // ����ģ�������ݲ����
    XlsxTmplServ.OBJ.loadTmplData(tmplClazz);
    XlsxTmplServ.OBJ.packUp(tmplClazz);
}

// ��֤����ģ����
XlsxTmplServ.OBJ.validateAll();
```

----

**BuildingTmpl ��**

1���� building.xlsx �ļ��ĵ� 1 ��ҳǩ��ʼ�����ݣ�

2�����彨�� Id��������Ϊ��ֵ��

3�����彨�����ͣ�������Ϊ�գ�����ֻ���ǣ�1��2��3��4���� 4 ��ֵ�е�һ����

4�����彨�����ƣ�����Ϊ��ֵ��

5�����彨��˵��������Ϊ��ֵ������󳤶Ȳ��ܳ��� 200 ���ַ���

6�����彨�� Id �ֵ䣬����ͨ������ Id ��ֵȡ�� BuildingTmpl ����

7�����彨�������ֵ䣬����ͨ����������ȡ�� BuildingTmpl �����б�

```
@FromXlsxFile(fileName = "building.xlsx", sheetIndex = 0)
public class BuildingTmpl extends AbstractXlsxTmpl {
    /** ���� Id */
    @OneToOne(groupName = "_Id")
    public XlsxInt _Id = new XlsxInt(false);
    /** �������� */
    @OneToMany(groupName = "_Type")
    public XlsxInt _typeInt = XlsxInt.createByEnum(false, 1, 1, 2, 3, 4);
    /** �������� */
    public XlsxStr _buildingName;
    /** ����˵�� */
    public XlsxStr _buildingDesc = new XlsxStr(true) {
        @Override
        public void validate() {
            super.validate();

            if (this.getStrVal() != null && 
                this.getStrVal().length() > 200) {
                throw new XlsxTmplError(this, "����˵������ 200 ���ַ�");
            }
        }
    };

    /** Id �ֵ� */
    @OneToOne(groupName = "_Id")
    public static Map<Integer, BuildingTmpl> _IdMap = new HashMap<>();
    /** �����ֵ� */
    @OneToMany(groupName = "_Type")
    public static Map<Integer, List<BuildingTmpl>> _typeMap = new HashMap<>();
}
```

----

**ʹ�� BuildingTmpl**

0��һ����Ҫȷ���Ѿ����ù���XlsxTmplServ.OBJ.packUp(...); ��һ����

1�����ݽ��� Id ��ȡ BuildingTmpl ����

2�����ݽ������ͻ�ȡ BuildingTmpl �����б�

```
BuildingTmpl tmplObj = BuildingTmpl._IdMap.get(1);
List<BuildingTmpl> tmplObjList = BuildingTmpl._typeMap.get(1);
```

----

**���ӵ� BuildingTmpl**

1���� building.xlsx �ļ��ĵ� 1 ��ҳǩ��ʼ�����ݣ�

2��A ��Ϊ���� Id��������Ϊ��ֵ��

3��B �� ~ D �У�Ϊ�����������ã�

```
@FromXlsxFile(fileName = "building.xlsx", sheetIndex = 0)
public class BuildingTmpl extends AbstractXlsxTmpl {
    /** ���� Id */
    public XlsxInt _Id = new XlsxInt(false);
    /** �������� */
    public FuncTmplObj _func;
}

// �������� @FromXlsxFile ע��
public class FuncTmplObj extends AbstractXlsxTmpl {
    /** ���� Id */
    public XlsxInt _funcId; // ����Ӧ B ��
    /** �������� */
    public XlsxStr _funcName; // ����Ӧ C ��
    /** ����˵�� */
    public XlsxStr _funcDesc; // ����Ӧ D ��
}
```

----

**�ٸ���һ��� BuildingTmpl**

1���� building.xlsx �ļ��ĵ� 1 ��ҳǩ��ʼ�����ݣ�

2��A ��Ϊ���� Id��������Ϊ��ֵ��

3��B �� ~ D �У�Ϊ�������� 1 ���ã�

4��E �� ~ G �У�Ϊ�������� 2 ���ã�

```
@FromXlsxFile(fileName = "building.xlsx", sheetIndex = 0)
public class BuildingTmpl extends AbstractXlsxTmpl {
    /** ���� Id */
    public XlsxInt _Id = new XlsxInt(false);
    /** �������� */
    @ElementNum(2)
    public XlsxArrayList<FuncTmplObj> _func;
}
```

----

ShopTmpl ����

1���� shop.xlsx �ļ��ĵ� 1 ��ҳǩ��ʼ�����ݣ�

2��A ��Ϊ�̵����ͣ�������Ϊ��ֵ��

3��B �� ~ F �У�Ϊ���� Id���������� Id ���飬����Ϊ 5��

4��B �о��Բ���Ϊ��ֵ�������̵�������Ӧ���� 1 �����ߣ�

```
@FromXlsxFile(fileName = "shop.xlsx", sheetIndex = 0)
public class ShopTmpl extends AbstractXlsxTmpl {
    /** �̵����� */
    public XlsxInt _typeInt = new XlsxInt(false);
    /** ���� Id �б� */
    @ElementNum(5)
    public XlsxArrayList<XlsxInt> _itemIdList = new XlsxArrayList(
        new XlsxInt(false)
    );
}
```

----

**SysLangTmpl ����**

0��һ����Ҫȷ���Ѿ����ù���XlsxTmplServ.OBJ._propMap.put("lang", "zh_CN");

1����Ҫ��ȡ i18n Ŀ¼�µ� zh_CN Ŀ¼�е� sysLang.xlsx �ļ���

2�����������ı��ֶΣ�

3������������ı��ֶΣ�

```
@FromXlsxFile(fileName = "i18n/${lang}/sysLang.xlsx")
public class SysLangTmpl extends AbstractXlsxTmpl {
    /** �����ı� */
    public XlsxStr _zhText;
    /** �������ı� */
    public XlsxStr _langText;
}
```