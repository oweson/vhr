package sea.top.itemt;//package sea.top.itemt;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * the class is create by @Author:oweson
// *
// * @Date：2019/8/5 21:48
// */
//public class ItemService {
//    /**
//            * 管理员获取菜单
//     * @return
//             */
//    public List<SysModuleVo> getAdminListModule() {
//        SysModuleExample example = new SysModuleExample();
//        //这个Example类是采用Mybatis生成实体的一起生成的，不清楚的童鞋可以百度一下
//        SysModuleExample.Criteria criteria = example.createCriteria();
//        criteria.andFlagEqualTo((byte) 1);
//
//        //既然是管理用户，理所当然加载出全部的功能菜单。fiag=1：表示有效菜单
//        List<SysModule> list = sysModuleDao.selectByExample(example);
//
//        // 1 最后返回的容器集合
//        List<SysModuleVo> resultList = new ArrayList<>();
//
//        //判断查询出来的菜单是否为空
//        if (list != null && list.size() > 0) {
//
//            //让属于相同的父级菜单进行归类
//            Map<String, List<SysModule>> map = new HashMap<>();
//
//            /**
//             * 第一步：让所有具有相同的父级code的子菜单，进行归类
//             */
//
//            //遍历所有查询出来的功能菜单
//            for (SysModule module : list) {
//
//                //根据pcode进行分类，pcode：父级菜单的code，pcode=0：表示该菜单是父级菜单
//                if (map.get(module.getPcode()) == null) {
//
//                    //创建一个存放相同父级菜单的子菜单的集合
//                    List<SysModule> chmudule = new ArrayList<>();
//
//                    //将当前对象存放到集合当中
//                    chmudule.add(module);
//
//                    //添加到Map当中，已每个菜单的父级pcode作为key
//                    map.put(module.getPcode(), chmudule);
//                } else {
//                    //否则，如果当前map当中有存放相同父级菜单的数据，则取出之前保存的子菜单，
//                    // 添加新的子菜单，然后添加在添加的map当中
//                    List<SysModule> modules = map.get(module.getPcode());
//
//                    //在之前的子菜单基础之上，添加新的子菜单
//                    modules.add(module);
//
//                    //添加到Map当中，已每个菜单的父级pcode作为key
//                    map.put(module.getPcode(), modules);
//
//                }
//            }
//
//            /**
//             * 第二步：遍历所有的父级菜单，通过父级菜单中的code找到对应的子菜单，并添加到集合当中。
//             */
//
//            //获取所有的父级菜单
//            List<SysModule> moduleList = map.get("0");
//
//            //遍历所有的父级菜单，通过父级菜单的中的code，获取所有的子菜单
//            for (SysModule module : moduleList) {
//                SysModuleVo moduleVo = new SysModuleVo();
//                moduleVo.setId(module.getId());
//                moduleVo.setCode(module.getCode());
//                moduleVo.setPcode(module.getPcode());
//                moduleVo.setCreatedate(module.getCreatedate());
//                moduleVo.setFlag(module.getFlag());
//                moduleVo.setTitle(module.getTitle());
//                moduleVo.setIntro(module.getIntro());
//                moduleVo.setListModule(map.get(module.getCode()));
//                resultList.add(moduleVo);
//            }
//        }
//
//        return resultList;
//
//    }
//}
