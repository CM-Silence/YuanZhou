import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from "@/views/Home.vue";

import HomePage from "@/views/front-views/HomePage.vue";

import Labs from "@/views/front-views/Labs.vue";
import AllLabs from "@/views/front-views/labs/AllLabs.vue";
import LabsShowView from "@/views/front-views/labs/LabsShowView.vue";

import News from "@/views/front-views/News.vue";
import Information from "@/views/front-views/news/Information.vue";
import Notices from "@/views/front-views/news/Notices.vue";
import NewsShowView from "@/views/front-views/news/NewsShowView.vue";

import Res from "@/views/front-views/Res.vue";
import Audio from "@/views/front-views/res/Audio.vue";
import Doc from "@/views/front-views/res/Doc.vue";
import Video from "@/views/front-views/res/Video.vue";
import Virtual from "@/views/front-views/res/Virtual.vue";
import ResShowView from "@/views/front-views/res/resShowView.vue";

import Shared from "@/views/front-views/Shared.vue";
import SharedLab from "@/views/front-views/shared/SharedLab.vue";
import SharedRes from "@/views/front-views/shared/SharedRes.vue";

import Training from "@/views/front-views/Training.vue";
import TrainingCenter from "@/views/front-views/training/trainingCenter.vue";
import TrainingShowView from "@/views/front-views/training/TrainingShowView.vue";

import UserCenter from "@/views/front-views/UserCenter.vue";
import MyCollection from "@/views/front-views/user-center/MyCollection.vue";
import MyCourse from "@/views/front-views/user-center/MyCourse.vue";
import MyExperiment from "@/views/front-views/user-center/MyExperiment.vue";
import MyInfo from "@/views/front-views/user-center/MyInfo.vue";
import MyMsg from "@/views/front-views/user-center/MyMsg.vue";

import DataPreview from "@/views/back-views/DataPreview.vue";
import DataExport from "@/views/back-views/data-preview/DataExport.vue";
import OverviewStatistics from "@/views/back-views/data-preview/OverviewStatistics.vue";
import TrainingAnalysis from "@/views/back-views/data-preview/TrainingAnalysis.vue";

import IntegratedManagement from "@/views/back-views/IntegratedManagement.vue";
import NewsManagement from "@/views/back-views/integrated-managment/NewsManagement.vue"
import PlatformPersonnel from "@/views/back-views/integrated-managment/PlatformPersonnel.vue";
import PlatformSetting from "@/views/back-views/integrated-managment/PlatformSetting.vue";

import MonitoringManagement from "@/views/back-views/MonitoringManagement.vue";
import DevicesMonitor from "@/views/back-views/monitoring-management/DevicesMonitor.vue";
import ResMonitor from "@/views/back-views/monitoring-management/ResMonitor.vue";
import TeachingMonitor from "@/views/back-views/monitoring-management/TeachingMonitor.vue";

import ResManagement from "@/views/back-views/ResManagement.vue";
import DevicesManagement from "@/views/back-views/res-management/DevicesManagement.vue";
import LabsApply from "@/views/back-views/res-management/LabsApply.vue";
import LabsManagement from "@/views/back-views/res-management/LabsManagement.vue";
import TeacherManagement from "@/views/back-views/res-management/TeacherManagement.vue";
import TrainingResManagement from "@/views/back-views/res-management/TrainingResManagement.vue";

import SystemInfo from "@/views/back-views/SystemInfo.vue";
import DataDictionary from "@/views/back-views/system-info/DataDictionary.vue";
import SystemLog from "@/views/back-views/system-info/SystemLog.vue";
import SystemUser from "@/views/back-views/system-info/SystemUser.vue";
import UserManagement from "@/views/back-views/system-info/UserManagement.vue";

import TrainingManagement from "@/views/back-views/TrainingManagement.vue";
import CourseManagement from "@/views/back-views/training-management/CourseManagement.vue";
import ProcessResults from "@/views/back-views/training-management/ProcessResults.vue";
import TeachingPlan from "@/views/back-views/training-management/TeachingPlan.vue";
import TrainingExperiment from "@/views/back-views/training-management/TrainingExperiment.vue";

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children:[
      {path: 'homePage', name: 'HomePage', component: HomePage},
      {path: 'labs', name: 'Labs', component: Labs, children: [
          {path: 'allLabs', name: 'AllLabs', component: AllLabs},
          {path: 'labsShowView', name: 'LabsShowView', component: LabsShowView},
        ]},
      {path: 'news', name: 'News', component: News, children:[
          {path: 'information', name: 'Information', component: Information},
          {path: 'notices', name: 'Notices', component: Notices},
          {path: 'newsShowView', name: 'NewsShowView', component: NewsShowView},
        ]},
      {path: 'res', name: 'Res', component: Res, children:[
          {path: 'resShowView', name: 'ResShowView', component: ResShowView},
          {path: 'audio', name: 'Audio', component: Audio},
          {path: 'doc', name: 'Doc', component: Doc},
          {path: 'video', name: 'Video', component: Video},
          {path: 'virtual', name: 'Virtual', component: Virtual},
        ]},
      {path: 'shared', name: 'Shared', component: Shared, children:[
          {path: 'sharedLab', name: 'SharedLab', component: SharedLab},
          {path: 'sharedRes', name: 'SharedRes', component: SharedRes},
        ]},
      {path: 'training', name: 'Training', component: Training, children: [
          {path: 'trainingCenter', name: 'TrainingCenter', component: TrainingCenter},
          {path: 'trainingShowView', name: 'TrainingShowView', component: TrainingShowView},
        ]},
      {path: 'userCenter', name: 'UserCenter', component: UserCenter, children:[
          {path: 'myCollection', name: 'MyCollection', component: MyCollection},
          {path: 'myCourse', name: 'MyCourse', component: MyCourse},
          {path: 'myExperiment', name: 'MyExperiment', component: MyExperiment},
          {path: 'myInfo', name: 'MyInfo', component: MyInfo},
          {path: 'myMsg', name: 'MyMsg', component: MyMsg},
        ]},
      {path: 'dataPreview', name:'DataPreview', component: DataPreview, children: [
          {path: 'dataExport', name: 'DataExport', component: DataExport},
          {path: 'overviewStatistics', name: 'OverviewStatistics', component: OverviewStatistics},
          {path: 'trainingAnalysis', name: 'TrainingAnalysis', component: TrainingAnalysis},
        ]},
      {path: 'integratedManagement', name:'IntegratedManagement', component: IntegratedManagement, children: [
          {path: 'newsManagement', name: 'NewsManagement', component: NewsManagement},
          {path: 'platformPersonnel', name: 'PlatformPersonnel', component: PlatformPersonnel},
          {path: 'platformSetting', name: 'PlatformSetting', component: PlatformSetting},
        ]},
      {path: 'monitoringManagement', name:'MonitoringManagement', component: MonitoringManagement, children: [
          {path: 'devicesMonitor', name: 'DevicesMonitor', component: DevicesMonitor},
          {path: 'resMonitor', name: 'ResMonitor', component: ResMonitor},
          {path: 'teachingMonitor', name: 'TeachingMonitor', component: TeachingMonitor},
        ]},
      {path: 'resManagement', name:'ResManagement', component: ResManagement, children: [
          {path: 'devicesManagement', name: 'DevicesManagement', component: DevicesManagement},
          {path: 'labsApply', name: 'LabsApply', component: LabsApply},
          {path: 'labsManagement', name: 'LabsManagement', component: LabsManagement},
          {path: 'teacherManagement', name: 'TeacherManagement', component: TeacherManagement},
          {path: 'trainingResManagement', name: 'TrainingResManagement', component: TrainingResManagement},
        ]},
      {path: 'systemInfo', name:'SystemInfo', component: SystemInfo, children: [
          {path: 'dataDictionary', name: 'DataDictionary', component: DataDictionary},
          {path: 'systemLog', name: 'SystemLog', component: SystemLog},
          {path: 'systemUser', name: 'SystemUser', component: SystemUser},
          {path: 'userManagement', name: 'UserManagement', component: UserManagement},
        ]},
      {path: 'trainingManagement', name:'TrainingManagement', component: TrainingManagement, children: [
          {path: 'courseManagement', name: 'CourseManagement', component: CourseManagement},
          {path: 'processResults', name: 'ProcessResults', component: ProcessResults},
          {path: 'teachingPlan', name: 'TeachingPlan', component: TeachingPlan},
          {path: 'trainingExperiment', name: 'TrainingExperiment', component: TrainingExperiment},
        ]},
    ]
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
