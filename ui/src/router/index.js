import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import('@/views/home/home'),
      redirect: '/flights',
      // redirect: 'member/user/passengers',
      children: [
        {
          path: '/news',
          name: 'news',
          meta: {
            title: '媒体中心',
            footer: true
          },
          component: () => import('@/views/news/news')
        },
        {
          path: '/news/detail/:id',
          name: 'news',
          meta: {
            title: '媒体中心',
            footer: true
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
                title: '关于易到',
                footer: true
              },
              component: () => import('@/views/brand/about-us')
            },
            {
              path: '/brand/faq',
              name: 'faq',
              meta: {
                title: '你问我答',
                footer: true
              },
              component: () => import('@/views/brand/faq')
            },
            {
              path: '/brand/join-us',
              name: 'join-us',
              meta: {
                title: '加入我们',
                footer: true
              },
              component: () => import('@/views/brand/join-us')
            },
            {
              path: '/brand/contact-us',
              name: 'contact-us',
              meta: {
                title: '联系我们',
                footer: true
              },
              component: () => import('@/views/brand/contact-us')
            }
          ]
        },
        {
          path: '/brand/our-team',
          name: 'our-team',
          meta: {
            title: '团队介绍',
            footer: true
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
                title: '易道保障',
                footer: true
              },
              component: () => import('@/views/terms/yidao-guarantee/yidao-guarantee')
            },
            {
              path: '/terms/legal-notices',
              name: 'legal-notices',
              meta: {
                title: '法律声明',
                footer: true
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
          redirect: '/flights/index',
          children: [
            {
              path: '/flights/index',
              name: 'index',
              meta: {
                title: '找机票',
                footer: true
              },
              component: () => import('@/views/flights/index')
            },
            {
              path: '/flights/where-to-go',
              name: 'where-to-go',
              meta: {
                title: '飞去哪',
                footer: true
              },
              component: () => import('@/views/flights/where-to-go')
            },
            {
              path: '/flights/when-to-go',
              name: 'when-to-go',
              meta: {
                title: '何时飞',
                footer: true
              },
              component: () => import('@/views/flights/when-to-go')
            }
          ]
        },
        {
          path: '/flights/timeline',
          name: 'timeline',
          meta: {
            title: '订机票'
          },
          component: () => import('@/views/flights/timeline/timeline')
        },
        {
          path: '/flights/booking',
          name: 'booking',
          meta: {
            title: '订票订单',
            footer: true
          },
          component: () => import('@/views/flights/booking/booking')
        },
        {
          path: '/hotel',
          name: 'hotel',
          meta: {
            title: '酒店',
            footer: true
          },
          component: () => import('@/views/hotel/hotel')
        },
        {
          path: '/hotel/result-list',
          name: 'result-list',
          meta: {
            title: '酒店'
          },
          component: () => import('@/views/hotel/result-list/result-list')
        },
        {
          path: '/hotel/hotel-detail',
          name: 'hotel-detail',
          meta: {
            title: '酒店',
            footer: true
          },
          component: () => import('@/views/hotel/hotel-detail/hotel-detail')
        },
        {
          path: '/hotel/hotel-booking',
          name: 'hotel-booking',
          meta: {
            title: '酒店',
            footer: true
          },
          component: () => import('@/views/hotel/hotel-booking/hotel-booking')
        },
        {
          path: '/member',
          name: 'member',
          meta: {
            title: '会员中心'
          },
          component: () => import('@/views/member/member'),
          children: [
            {
              path: '/member/account/sign-in',
              name: 'sign-in',
              meta: {
                title: '登录'
              },
              component: () => import('@/views/member/account/sign-in')
            },
            {
              path: '/member/account/sign-up',
              name: 'sign-up',
              meta: {
                title: '注册'
              },
              component: () => import('@/views/member/account/sign-up')
            },
            {
              path: '/member/account/forgot-password',
              name: 'forgot-password',
              meta: {
                title: '忘记密码'
              },
              component: () => import('@/views/member/account/forgot-password')
            },
            {
              path: '/member/account/add-email',
              name: 'add-email',
              meta: {
                title: '添加邮箱'
              },
              component: () => import('@/views/member/account/add-email')
            },
            {
              path: '/member/account/change-phone',
              name: 'change-phone',
              meta: {
                title: '修改手机'
              },
              component: () => import('@/views/member/account/change-phone')
            },
            {
              path: '/member/user',
              name: 'user',
              meta: {
                title: '用户中心'
              },
              component: () => import('@/views/member/user/user'),
              children: [
                {
                  path: '/member/user/my-booking',
                  name: 'my-booking',
                  meta: {
                    title: '我的订单-机票订单'
                  },
                  component: () => import('@/views/member/user/my-booking/my-booking')
                },
                {
                  path: '/member/user/my-hotel-booking',
                  name: 'my-hotel-booking',
                  meta: {
                    title: '我的订单-酒店订单'
                  },
                  component: () => import('@/views/member/user/my-hotel-booking/my-hotel-booking')
                },
                {
                  path: '/member/user/my-cashback',
                  name: 'my-cashback',
                  meta: {
                    title: '我的返现'
                  },
                  component: () => import('@/views/member/user/my-cashback/my-cashback')
                },
                {
                  path: '/member/user/account-setting',
                  name: 'account-setting',
                  meta: {
                    title: '账户设置'
                  },
                  component: () => import('@/views/member/user/account-setting/account-setting')
                },
                {
                  path: '/member/user/contacts',
                  name: 'contacts',
                  meta: {
                    title: '常用联系人'
                  },
                  component: () => import('@/views/member/user/contacts/contacts')
                },
                {
                  path: '/member/user/passengers',
                  name: 'passengers',
                  meta: {
                    title: '常用旅客'
                  },
                  component: () => import('@/views/member/user/passengers/passengers')
                },
                {
                  path: '/member/user/coupons',
                  name: 'coupons',
                  meta: {
                    title: '优惠券'
                  },
                  component: () => import('@/views/member/user/coupons/coupons')
                }
              ]
            }
          ]
        }
      ]
    }
  ]
})
