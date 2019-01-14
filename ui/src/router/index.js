import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/home/home'),
      redirect: '/hotel',
      children: [
        {
          path: '/news',
          name: 'news',
          meta: {
            title: '媒体中心'
          },
          component: () => import('@/views/news/news')
        },
        {
          path: '/news/detail/:id',
          name: 'news',
          meta: {
            title: '媒体中心'
          },
          component: () => import('@/views/news/news-detail/news-detail')
        },
        {
          path: '/brand',
          name: 'brand',
          meta: {
            title: '易到'
          },
          component: () => import('@/views/brand/brand'),
          children: [
            {
              path: '/brand/about-us',
              name: 'about-us',
              meta: {
                title: '关于易到'
              },
              component: () => import('@/views/brand/about-us')
            },
            {
              path: '/brand/faq',
              name: 'faq',
              meta: {
                title: '你问我答'
              },
              component: () => import('@/views/brand/faq')
            },
            {
              path: '/brand/join-us',
              name: 'join-us',
              meta: {
                title: '加入我们'
              },
              component: () => import('@/views/brand/join-us')
            },
            {
              path: '/brand/contact-us',
              name: 'contact-us',
              meta: {
                title: '联系我们'
              },
              component: () => import('@/views/brand/contact-us')
            }
          ]
        },
        {
          path: '/brand/our-team',
          name: 'our-team',
          meta: {
            title: '团队介绍'
          },
          component: () => import('@/views/brand/our-team')
        },
        {
          path: '/terms',
          name: 'terms',
          meta: {
            title: '保障'
          },
          component: () => import('@/views/terms/terms'),
          children: [
            {
              path: '/terms/yidao-guarantee',
              name: 'yidao-guarantee',
              meta: {
                title: '易道保障'
              },
              component: () => import('@/views/terms/yidao-guarantee/yidao-guarantee')
            },
            {
              path: '/terms/legal-notices',
              name: 'legal-notices',
              meta: {
                title: '法律声明'
              },
              component: () => import('@/views/terms/legal-notices')
            }
          ]
        },
        {
          path: '/flights',
          name: 'flights',
          meta: {
            title: '机票'
          },
          component: () => import('@/views/flights/flights'),
          children: [
            {
              path: '/flights/index',
              name: 'index',
              meta: {
                title: '找机票'
              },
              component: () => import('@/views/flights/index')
            }
            // {
            //   path: '/flights/where-to-go',
            //   name: 'where-to-go',
            //   meta: {
            //     title: '飞去哪'
            //   },
            //   component: () => import('@/views/flights/where-to-go')
            // },
            // {
            //   path: '/flights/when-to-go',
            //   name: 'when-to-go',
            //   meta: {
            //     title: '何时飞'
            //   },
            //   component: () => import('@/views/flights/when-to-go')
            // }
          ]
        },
        {
          path: '/hotel',
          name: 'hotel',
          meta: {
            title: '酒店'
          },
          component: () => import('@/views/hotel/hotel')
        }
      ]
    }
  ]
})
